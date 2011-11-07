package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.TaskInstance
import com.opencsi.ghrm.domain.User
import com.opencsi.ghrm.domain.Task
import com.opencsi.ghrm.domain.Project
// Mail:
import com.opencsi.ghrm.services.MailService

class TaskInstanceController {

    def index = { }

    def save = {
        def taskInstance = new TaskInstance()
        try{
            if ((params.days).toFloat() > 0)
            {
                taskInstance.project = Project.get(params.projectid)
                taskInstance.user = User.get(params.userid)
                taskInstance.task = Task.get(params.taskid)
                taskInstance.days = (params.days).toFloat()

                if(taskInstance.save(onFailError: true, flush:true)) {
                    // Send a notification mail:
                    def message = "Hello,\n the user '${taskInstance.user.firstname} ${taskInstance.user.lastname}' has a new task corresponding : '${taskInstance.task.name}' for the project : '${taskInstance.project.name}'.\n\nRegards.\n\n\nINFO: do not answer to this email."
                    MailService mail = new MailService()
                    flash.message = mail.sendMail("admin@opencsi.com","[GHRM] A new task for an user",message)//admin@opencsi.com
                    // redirect:
                    //redirect(controller: 'project', action: 'show', id: params.projectid)
                } else {
                    flash.message = "Error " + taskInstance.errors.allErrors.join(',')
                    //redirect(controller: 'project', action: 'show', id: params.projectid)
                }
            }
            //redirect(controller: 'project', action: 'show', id: params.projectid)
        }
        catch(Exception e)
        {
            flash.message = "${message(code:'task.error.submit')}"
            //redirect(controller: 'project', action: 'show', id: params.projectid)
        }
        redirect(controller: 'project', action: 'show', id: params.projectid)
    }
}


