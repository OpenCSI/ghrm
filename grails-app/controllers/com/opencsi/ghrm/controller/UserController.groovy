package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.User
import com.opencsi.security.ShiroUser
import com.opencsi.security.ShiroRole
import org.apache.shiro.crypto.hash.*

class UserController {

    
    def create = {
        
    }

    def list = {
         params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def save = {
        def userInstance = new User()
        def shiroUser = new ShiroUser()
        def error = 0
        
        
        userInstance.uid = params.uid
        userInstance.firstname = params.firstname
        userInstance.lastname = params.lastname
        userInstance.email = params.email
        

        if (params.password != params.repassword) {
            flash.message = "Password doesn't match"
            error = 1
        }
        
        if (params.password.length() < 6) {
            flash.message = "Password is too short"
            error = 1
        }


        if(!error) {
            if(userInstance.save(onFailError: true, flush: true)) {
                shiroUser.username = params.uid
                shiroUser.passwordHash = new Sha256Hash(params.password).toHex()
                
                shiroUser.addToRoles(ShiroRole.findByName(params.role))
                shiroUser.save(flush: true)
                redirect(controller: 'user', action: 'list')

            }
            //render(view: 'create', model: [userInstance: userInstance])
        } else {
            render(view: 'create', model: [userInstance: userInstance])
        }
    }

    def delete = {
        
    }
}
