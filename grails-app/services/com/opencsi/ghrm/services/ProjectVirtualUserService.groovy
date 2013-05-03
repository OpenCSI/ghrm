package com.opencsi.ghrm.services

import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.User
import com.opencsi.ghrm.domain.TaskReport
import com.opencsi.ghrm.domain.TaskInstance

import com.opencsi.ghrm.domain.ProjectVirtualUser

class ProjectVirtualUserService {
    
    static def getByUser(User user){
        def tasksInstance = TaskInstance.findAllByUser(user)
        def list = [:]
        list["actif"] = [:]
        list["passif"] = [:]
        if (!user.showIDLE)
            list["passif"] = false
        tasksInstance.each{ tInstance ->
            Float progress = 0.0
                    def tasksReport = TaskReport.findAllByTaskInstance(tInstance)
                    tasksReport.each{ tReport ->
                        progress += tReport.days
                    }
                    if (tInstance.project.status != Project.STATUS_CLOSE){
                        if (!list["actif"].containsKey(tInstance.project.customer.name))
                            list["actif"][tInstance.project.customer.name] = []
                        list["actif"][tInstance.project.customer.name].add(new ProjectVirtualUser(project: tInstance.project.name,ID: tInstance.project.id,
                         progress: progress,max: tInstance.days,label: tInstance.project.label))
                    }
                    else if(user.showIDLE){
                        if (!list["passif"].containsKey(tInstance.project.customer.name))
                            list["passif"][tInstance.project.customer.name] = []
                        list["passif"][tInstance.project.customer.name].add(new ProjectVirtualUser(project: tInstance.project.name,ID: tInstance.project.id,
                         progress: progress,max: tInstance.days,label: tInstance.project.label))
                    }
        }
        return list
    }
    
}
