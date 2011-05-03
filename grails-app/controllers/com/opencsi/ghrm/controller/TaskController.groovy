package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.Task

class TaskController {

    def show = {
        [task: Task.findById(params.id)]
    }

    def create = {
        
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [TaskInstanceList: Task.list(params), TaskInstanceTotal: Task.count()]
    }

}
