package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.User

import com.opencsi.ghrm.services.UserService
import com.opencsi.ghrm.services.TaskInstanceService
import com.opencsi.ghrm.services.ProjectVirtualUserService

import com.opencsi.security.ShiroUser

import org.apache.shiro.crypto.hash.Sha256Hash

class MeController {
    def UserService userService
    TaskInstanceService taskInstanceService

    def index = { }

    def password = {
        if (params.modify)
        {
            def shiroUser = ShiroUser.findByUsername(userService.getAuthenticatedUserName())
            if (shiroUser.passwordHash == new Sha256Hash(params.oldpassword).toHex() )
            {
                if(params.password == params.repassword)
                {
                    if (params.password.length() >= 6)
                    {
                        shiroUser.passwordHash = new Sha256Hash(params.password).toHex()
                        shiroUser.save(flush:true)
                        flash.message = "${message(code : 'user.modify.password.done')}"
                    }
                    else
                        flash.message = "${message(code : 'user.modify.error.password.short')}"
                }
                else
                    flash.message = "${message(code : 'user.modify.error.password.match')}"
            }
            else
                flash.message = "${message(code : 'user.modify.error.password.old')}"
        }

        [projectList: ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))]
    }

    def modify = {       
        def userInstance = User.findByUid(userService.getAuthenticatedUserName())
        if (params.modify)
        {
            userInstance.firstname = params.firstname
            userInstance.lastname  = params.lastname
            userInstance.email     = params.email
            if (params?.showIDLE == "on")
				userInstance.showIDLE  = true
			else
				userInstance.showIDLE = false
            userInstance.save(flush:true)
            flash.message = "${message(code:'user.modify')}"
        }
        def listProject = ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        
        [firstname : userInstance.firstname,email : userInstance.email,
            lastname : userInstance.lastname,projectList: listProject, showIDLE: userInstance.showIDLE]
    }
}
