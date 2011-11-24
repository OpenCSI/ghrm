package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.Project
import com.opencsi.security.ShiroUser
import com.opencsi.ghrm.services.UserService

import org.apache.shiro.crypto.hash.Sha256Hash

class MeController {
    def UserService userService

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
        [projectList: Project.list()]
    }

    def modify = {

        [projectList: Project.list()]
    }
}
