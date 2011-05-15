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
        def user = ShiroUser.findByUsername(getAuthenticatedUserName())
        return user.roles.name.contains(role)
    }

    def checkPermission(String userName, String role) {
        def user = ShiroUser.findByUsername(userName)
        return user.roles.name.contains(role)
    }
}
