package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.Customer
import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.TaskInstance

class CustomerController {
    static allowedMethods = [save: "POST", update: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def show = {
        def customer = Customer.get(params.id)
        [customer: customer]
    }
    
    def create = {
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
        [customerInstanceList: Customer.list(params), customerInstanceTotal: Customer.count()]
    }

    def modify = {
        def  customerInstance = Customer.get(params.id)
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
            postal_code: customerInstance.postalCode, street: customerInstance.street]
    }

    def delete = {
        def customerInstance = Customer.get(params.id)
        if (customerInstance)
        {
            // find all projects and tasks:
            def project = Project.findAllByCustomer(customerInstance)
            // projects:
            for (proj in project)
            {
                def tasks = TaskInstance.findAllByProject(proj)
                // tasks:
                for (task in tasks)
                    task.delete()
                proj.delete()
            }
            // then, remove the customer:
            customerInstance.delete()
            flash.message = "${message(code : 'customer.delete')}"
        }else
            flash.message = "${message(code : 'customer.delete.error')}"
        redirect(action:"list")
    }
}
