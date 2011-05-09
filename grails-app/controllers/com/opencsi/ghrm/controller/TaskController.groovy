package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.Task

class TaskController {

    def show = {
        [task: Task.findById(params.id)]
    }

    def create = {
        
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
        [taskInstanceList: Task.list(params), taskInstanceTotal: Task.count()]
    }

}
