package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.Customer
import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.TaskInstance
import com.opencsi.ghrm.domain.Task
import com.opencsi.ghrm.domain.User

import com.opencsi.ghrm.services.TaskInstanceService
import com.opencsi.ghrm.services.UserService
import com.opencsi.ghrm.services.ProjectVirtualUserService

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class CustomerController {
    //static allowedMethods = [save: "POST", update: "POST"]

    // Export service provided by Export plugin
    def exportService
    TaskInstanceService taskInstanceService

    def index = {
        redirect(action: "list", params: params)
    }
    
    def create = {
        [projectList: ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))]
    }

    def save = {
        def customerInstance = new Customer(params)
        if (customerInstance.save(flush: true)) {
            flash.message = "${message(code: 'customer.create', args: [message(code: 'customer.label', default: 'customer'), customerInstance.id])}"
            redirect(action: "list", id: customerInstance.id)
        }
        else {
            render(view: "create", model: [customerInstance: customerInstance])
        }
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        // Export :
         if(params?.format && params.format != "html")
         {
            response.contentType = ConfigurationHolder.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=task.list.${params.extension}")
            List fields = ['name','city','street','postalCode']
            Map labels = ['name' : 'Name','city' : 'City', 'street' : 'Street','postalCode': 'Postal Code']
            exportService.export(params.format, response.outputStream,Customer.list(params), fields, labels,[:],[:])
         }
        def listProject = ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        
        [customerInstanceList: Customer.list(params), customerInstanceTotal: Customer.count(),projectList: listProject]
    }

    def modify = {
        def customerInstance
        boolean ok = true
        try{
            customerInstance = Customer.get(params.id)
        }catch(Exception e)
        {
            flash.message = "${message(code:'customer.delete.error')}"
            redirect(action:'list')
        }
        if (params.modify)
        {
            // retreive datas:
            customerInstance.name = params.name
            customerInstance.city = params.city
            customerInstance.postalCode = params.postal_code
            customerInstance.street = params.street
            if(!params.picture.empty){
                if (params.picture.getContentType() =~ /image/){
                    customerInstance.picture = params.picture.getBytes()
                    customerInstance.typePicture = params.picture.getContentType()
                }
                else
                {
                    flash.message = "${message(code:'customer.save.error.picture')}"
                    ok = false
                }
            }
            // save them:
            if (ok){
                customerInstance.save()
                flash.message = "${message(code : 'customer.modify')}"
                redirect(action:'list')
            }
        }
        def listProject = ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        
        [id: params.id, name: customerInstance.name, city: customerInstance.city,
            postal_code: customerInstance.postalCode, street: customerInstance.street,
            picture: customerInstance.picture.encodeBase64(), typePicture: customerInstance.typePicture
            , projectList: listProject]
    }

    def confirm ={
        [id : params.id]
    }

    def delete = {
        def customerInstance
        try{
            customerInstance = Customer.get(params.id)
        }catch(Exception e)
        {
            flash.message = "${message(code:'customer.delete.error')}"
            redirect(action:'list')
        }
        if (customerInstance)
        {
            // find all projects and tasks:
            def project = Project.findAllByCustomer(customerInstance)
            // projects:
            for (proj in project)
            {
                def tasksInstances = TaskInstance.findAllByProject(proj)
                // tasks:
                for (taskInstance in tasksInstances)
                {
                    taskInstance.reports.each{
                        it.delete()
                    }
                    /*taskInstance.task.each{
                        it.delete()
                    }*/
                    taskInstance.delete()
                }
                proj.delete()
            }
            // then, remove the customer:
            customerInstance.delete(failOnError:true)
            flash.message = "${message(code : 'customer.delete')}"
        }else
            flash.message = "${message(code : 'customer.delete.error')}"
        redirect(action:"list")
    }
    
}
