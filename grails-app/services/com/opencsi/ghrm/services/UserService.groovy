package com.opencsi.ghrm.services
import org.apache.shiro.SecurityUtils
import com.opencsi.ghrm.domain.User
import com.opencsi.security.*

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

    def checkCurrentUserPermission(String role) {
        if (getAuthenticatedUserName()) {
            def user = ShiroUser.findByUsername(getAuthenticatedUserName())
            return user.roles.name.contains(role)
        } else {
            return false
        }
    }
}
