package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.Task
import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.User

import com.opencsi.ghrm.services.TaskInstanceService
import com.opencsi.ghrm.services.UserService

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class TaskController {
    // Export service provided by Export plugin
    def exportService
    TaskInstanceService taskInstanceService

    def show = {
        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        [task: Task.findById(params.id),projectList: Tasks.project]
    }

    def create = {
        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        [projectList: Tasks.project]
    }

    def save = {
        def task = new Task()
        task.name = params.name
        task.label = params.label
        task.description = params.description

        if(task.save(onFailError: true, flush:true)) {
            redirect(action: 'list')
        } else {
            render(view:'create', model: [taskInstance: task])
        }
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        // Export :
         if(params?.format && params.format != "html")
         {
            response.contentType = ConfigurationHolder.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=task.list.${params.extension}")
            List fields = ['createat','label','name','description']
            Map labels = ['createat' : 'Created at','label' : 'Label', 'name' : 'Name','description': 'Description']
            exportService.export(params.format, response.outputStream,Task.list(params), fields, labels,[:],[:])
         }

        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        [taskInstanceList: Task.list(params), taskInstanceTotal: Task.count(),projectList: Tasks.project]
    }

    def modify = {
        def taskInstance
        try{
            taskInstance = Task.get(params.id)
        }catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
            redirect(action:'list')
        }
        if (params.modify)
        {
            // retreive datas:
            taskInstance.name = params.name
            taskInstance.label = params.label
            taskInstance.description = params.description
            // save them:
            taskInstance.save()
            flash.message = "${message(code : 'task.modify')}"
            redirect(action:'list')
        }

        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        [id: params.id, name: taskInstance.name, label: taskInstance.label,
            description: taskInstance.description,projectList: Tasks.project]
    }

}
