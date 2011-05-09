package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.TaskInstance
import com.opencsi.ghrm.domain.User
import com.opencsi.ghrm.domain.Task
import com.opencsi.ghrm.domain.Project

class TaskInstanceController {

    def index = { }

    def save = {
        def taskInstance = new TaskInstance()
        taskInstance.project = Project.get(params.projectid)
        taskInstance.user = User.get(params.userid)
        taskInstance.task = Task.get(params.taskid)
        taskInstance.hours = (params.hours).toInteger()

        if(taskInstance.save(onFailError: true, flush:true)) {
            redirect(controller: 'project', action: 'show', id: params.projectid)
        } else {
            flash.message = "Error " + taskInstance.errors.allErrors.join(',')
            redirect(controller: 'project', action: 'show', id: params.projectid)
        }
    }
}


