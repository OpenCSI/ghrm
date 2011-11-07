package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.Customer
import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.TaskInstance
import com.opencsi.ghrm.domain.Task
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class CustomerController {
    //static allowedMethods = [save: "POST", update: "POST"]

    // Export service provided by Export plugin
    def exportService

    def index = {
        redirect(action: "list", params: params)
    }

    def show = {
        def customer = Customer.get(params.id)
        [customer: customer,projectList: Project.list()]
    }
    
    def create = {
        [projectList: Project.list()]
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
        [customerInstanceList: Customer.list(params), customerInstanceTotal: Customer.count(),projectList: Project.list()]
    }

    def modify = {
        def customerInstance
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
            // save them:
            customerInstance.save()
            flash.message = "${message(code : 'customer.modify')}"
            redirect(action:'list')
        }
        [id: params.id, name: customerInstance.name, city: customerInstance.city,
            postal_code: customerInstance.postalCode, street: customerInstance.street,projectList: Project.list()]
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
