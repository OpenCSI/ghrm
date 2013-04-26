package com.opencsi.ghrm.services

import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.User
import com.opencsi.ghrm.domain.TaskReport
import com.opencsi.ghrm.domain.TaskInstance

import com.opencsi.ghrm.domain.ProjectVirtualUser

class ProjectVirtualUserService {
    
    static def getByUser(User user){
        def tasksInstance = TaskInstance.findAllByUser(user)
        java.util.ArrayList<ProjectVirtualUser> list = []
        tasksInstance.each{ tInstance ->
            Float progress = 0.0
            if (!user.showIDLE)
            {
                if (tInstance.project.status != Project.STATUS_CLOSE)
                {
                    def tasksReport = TaskReport.findAllByTaskInstance(tInstance)
                    tasksReport.each{ tReport ->
                        progress += tReport.days
                    }
                    list.add(new ProjectVirtualUser(project: tInstance.project.name,ID: tInstance.project.id,customer: tInstance.project.customer.name,
                     progress: progress,max: tInstance.days,actif: tInstance.project.status,label: tInstance.project.label))   
                }
            }
            else
            {
                def tasksReport = TaskReport.findAllByTaskInstance(tInstance)
                tasksReport.each{ tReport ->
                    progress += tReport.days
                }
                list.add(new ProjectVirtualUser(project: tInstance.project.name,ID: tInstance.project.id,customer: tInstance.project.customer.name,
                         progress: progress,max: tInstance.days,actif: tInstance.project.status,label: tInstance.project.label))
            }
        }
        return list
    }
    
}
