package com.opencsi.ghrm.controller

import com.opencsi.ghrm.services.UserService
import com.opencsi.ghrm.domain.User
import com.opencsi.security.ShiroUser
import com.opencsi.security.ShiroRole
import org.apache.shiro.crypto.hash.*
import groovy.sql.Sql

class UserController {

    
    def create = {
       // flash.message = ""
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

        flash.message = ""
        // Passwod:
        if (params.password != params.repassword) {
            flash.message += "Password doesn't match<br>"
            error = 1
        }
        
        if (params.password.length() < 6) {
            flash.message += "Password is too short<br>"
            error = 1
        }
        // others fields (car < 2):
        if (params.uid.length() < 2) {
            flash.message += "uid is too short<br>"
            error = 1
        }
        if (params.firstname.length() < 2) {
            flash.message += "Firstname is too short<br>"
            error = 1
        }

        if (params.lastname.length() < 2) {
            flash.message += "Lastname is too short<br>"
            error = 1
        }

        if ( params.email.length() < 2) {
            flash.message += "Email is too short<br>"
            error = 1
        }

        if(!error) {
            if(userInstance.save(onFailError: true, flush: true)) {
                shiroUser.username = params.uid
                shiroUser.passwordHash = new Sha256Hash(params.password).toHex()
                
                shiroUser.addToRoles(ShiroRole.findByName(params.role))
                shiroUser.save(flush: true)
                redirect(controller: 'user', action: 'list')

            }else
            render(view: 'create', model: [userInstance: userInstance])
        } else {
            render(view: 'create', model: [userInstance: userInstance])
        }
    }

    def confirm = {
        [id : params.id]
    }

    def modify = {
        // show user:
        def userInstance = User.get(params.id)
        // modification:
        if (params.modify)
        {
            // retreive datas:
            userInstance.uid = params.uid
            userInstance.firstname = params.firstname
            userInstance.lastname = params.lastname
            userInstance.email = params.email
            userInstance.save()
            flash.message = "${message(code:'user.modify')}"
            redirect(action:'list')
        }
        [uid : userInstance.uid, firstname : userInstance.firstname,
            ,email : userInstance.email,lastname : userInstance.lastname,
            id : params.id]
    }


    def delete = {
        def user = User.get(params.id)
        def currentUser = new UserService()
        if (currentUser.getAuthenticatedUserName() == user.toString())
        {
            flash.message = "${message(code:'user.delete.error.yourself')}"
        }
        else
        {
            def userShiro = ShiroUser.findByUsername(user.toString())
            if ( (user)&&(userShiro) )
            {
                user.delete()
                userShiro.delete()
                flash.message = "${message(code:'user.delete')}"
            }else
                flash.message = "${message(code:'user.delete.error')}"
        }
        redirect(action: "list")
    }
}
