package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.TaskInstance
import com.opencsi.ghrm.domain.TaskReport
import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.User

import com.opencsi.ghrm.services.UserService

import com.opencsi.security.ShiroUser
import com.opencsi.security.ShiroRole

import org.apache.shiro.crypto.hash.Sha256Hash
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class UserController {
    // Export service provided by Export plugin
    def exportService
    
    def create = {
       // flash.message = ""
       [projectList: Project.list()]
    }

    def list = {
         params.max = Math.min(params.max ? params.int('max') : 10, 100)
         // Export :
         if(params?.format && params.format != "html")
         {
            response.contentType = ConfigurationHolder.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=user.list.${params.extension}")
            List fields = ['id','name','email','uid']
            Map labels = ['id' : 'ID','name' : 'Name', 'email' : 'Email','uid': 'UID']
            exportService.export(params.format, response.outputStream,User.list(params),fields,labels, [:], [:])
         }
        [userInstanceList: User.list(params), userInstanceTotal: User.count(),projectList: Project.list()]
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
            flash.message += "${message(code : 'user.modify.error.password.match')}"
            error = 1
        }
        
        if (params.password.length() < 6) {
            flash.message += "${message(code : 'user.modify.error.password.short')}"
            error = 1
        }
        // others fields (car < 2):
        if (params.uid.length() < 2) {
            flash.message += "${message(code : 'user.modify.error.uid.short')}"
            error = 1
        }
        if (params.firstname.length() < 2) {
            flash.message += "${message(code : 'user.modify.error.firstname.short')}"
            error = 1
        }

        if (params.lastname.length() < 2) {
            flash.message += "${message(code : 'user.modify.error.lastname.short')}"
            error = 1
        }

        if ( params.email.length() < 2) {
            flash.message += "${message(code : 'user.modify.error.email.short')}"
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
        def userInstance, userShiroInstance
        try{
            userInstance = User.get(params.id)
            userShiroInstance = ShiroUser.findByUsername(userInstance.uid)
        }catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
            redirect(action:'list')
        }
        // modification:
        if (params.modify)
        {
            // retreive datas:
            userInstance.firstname = params.firstname
            userInstance.lastname = params.lastname
            userInstance.email = params.email
            userInstance.save()
            try{
                // multiple entries:
                params.role.each{
                    if (it)
                    {
                        def userShiro = ShiroUser.findByUsername(userInstance.uid)
                        userShiro.addToRoles(ShiroRole.findByName(it))
                        userShiro.save()
                    }
                }
            }catch(Exception e)
            {
                // one entry:
                if (params?.role)
                {
                    def userShiro = ShiroUser.findByUsername(userInstance.uid)
                    userShiro.addToRoles(ShiroRole.findByName(params.role))
                    userShiro.save()
                }
            }
            flash.message = "${message(code:'user.modify')}"
            redirect(action:'list')
        }
        [uid : userInstance.uid, firstname : userInstance.firstname,
            ,email : userInstance.email,lastname : userInstance.lastname,
            id : params.id,projectList: Project.list(),roles : userShiroInstance]
    }

    def deleteRule = {
        try{
            def userInstance = User.get(params.id)
            def userShiroInstance = ShiroUser.findByUsername(userInstance.uid)
            // delete the selected rule:
            if (params.modify)
            {
                userShiroInstance.removeFromRoles(ShiroRole.get(params.rule))
                flash.message = "${message(code:'user.modify')}"
            }
        }catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
        }
        redirect(action:'modify', params:["id":params.id])
    }


    def delete = {
        def user
        try{
            user = User.get(params.id)
        }catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
            redirect(action: "list")
        }
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
                // delete work done by the user:
                def tasks = TaskInstance.findAllByUser(user)
                tasks.each{task ->
                    def reports = TaskReport.findAllByTaskInstance(task)
                    reports.each{ report ->
                        report.delete()
                    }
                    task.delete()
                }
                // delete the user:
                user.delete()
                userShiro.delete()
                flash.message = "${message(code:'user.delete')}"
            }else
                flash.message = "${message(code:'user.delete.error')}"
        }
        redirect(action: "list")
    }
}
