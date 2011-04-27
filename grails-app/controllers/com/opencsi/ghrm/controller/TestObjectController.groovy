package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.TestObject

class TestObjectController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        flash.message = "toto"
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [testObjectInstanceList: TestObject.list(params), testObjectInstanceTotal: TestObject.count()]
    }

    def create = {
        def testObjectInstance = new TestObject()
        testObjectInstance.properties = params
        return [testObjectInstance: testObjectInstance]
    }

    def save = {
        def testObjectInstance = new TestObject(params)
        if (testObjectInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'testObject.label', default: 'TestObject'), testObjectInstance.id])}"
            redirect(action: "show", id: testObjectInstance.id)
        }
        else {
            render(view: "create", model: [testObjectInstance: testObjectInstance])
        }
    }

    def show = {
        def testObjectInstance = TestObject.get(params.id)
        if (!testObjectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testObject.label', default: 'TestObject'), params.id])}"
            redirect(action: "list")
        }
        else {
            [testObjectInstance: testObjectInstance]
        }
    }

    def edit = {
        def testObjectInstance = TestObject.get(params.id)
        if (!testObjectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testObject.label', default: 'TestObject'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [testObjectInstance: testObjectInstance]
        }
    }

    def update = {
        def testObjectInstance = TestObject.get(params.id)
        if (testObjectInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (testObjectInstance.version > version) {
                    
                    testObjectInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'testObject.label', default: 'TestObject')] as Object[], "Another user has updated this TestObject while you were editing")
                    render(view: "edit", model: [testObjectInstance: testObjectInstance])
                    return
                }
            }
            testObjectInstance.properties = params
            if (!testObjectInstance.hasErrors() && testObjectInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'testObject.label', default: 'TestObject'), testObjectInstance.id])}"
                redirect(action: "show", id: testObjectInstance.id)
            }
            else {
                render(view: "edit", model: [testObjectInstance: testObjectInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testObject.label', default: 'TestObject'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def testObjectInstance = TestObject.get(params.id)
        if (testObjectInstance) {
            try {
                testObjectInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'testObject.label', default: 'TestObject'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'testObject.label', default: 'TestObject'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'testObject.label', default: 'TestObject'), params.id])}"
            redirect(action: "list")
        }
    }
}
