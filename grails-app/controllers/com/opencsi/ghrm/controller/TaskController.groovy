package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.Task

class TaskController {

    def show = {
        [task: Task.findById(params.id)]
    }

    def create = {
        
    }

}
