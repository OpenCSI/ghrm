package com.opencsi.ghrm.services
import org.apache.shiro.SecurityUtils

class UserService {

    static transactional = true

    def serviceMethod() {

    }

    def getAuthenticatedUserName() {

        def subject = SecurityUtils.subject
        def returnValue = null
        
        if (subject != null) {
            def principal = subject.principal
            if (principal != null) {
                returnValue = principal.toString()
            }
        }
        return returnValue
    }
}
