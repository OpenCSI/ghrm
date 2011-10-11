package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.Task
import com.opencsi.ghrm.domain.Project

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class TaskController {
    // Export service provided by Export plugin
    def exportService

    def show = {
        [task: Task.findById(params.id),projectList: Project.list()]
    }

    def create = {
        [projectList: Project.list()]
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
        [taskInstanceList: Task.list(params), taskInstanceTotal: Task.count(),projectList: Project.list()]
    }

    def modify = {
        def  taskInstance = Task.get(params.id)
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
        [id: params.id, name: taskInstance.name, label: taskInstance.label,
            description: taskInstance.description,projectList: Project.list()]
    }

}
