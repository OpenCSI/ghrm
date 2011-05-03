package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.Customer

class CustomerController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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
        def CustomerInstance = new Customer(params)
        if (CustomerInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'Customer.label', default: 'Customer'), CustomerInstance.id])}"
            redirect(action: "list", id: CustomerInstance.id)
        }
        else {
            flash.message = "Error " + CustomerInstance.errors.allErrors.join(',')
            render(view: "create", model: [CustomerInstance: CustomerInstance])
        }

    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [customerInstanceList: Customer.list(params), customerInstanceTotal: Customer.count()]
    }
}
