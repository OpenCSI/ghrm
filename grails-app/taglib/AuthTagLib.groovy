import com.opencsi.ghrm.services.UserService

class AuthTagLib {
    def static namespace="auth"
    UserService userService

    def isEmployee = { attrs,body ->
        if(userService.checkCurrentUserPermission('employee')) {
            out << body()
        }
    }

    def isAdmin = { attrs, body ->
        if(userService.checkCurrentUserPermission('admin')) {
            out << body()
        }
    }

    def isProjectLeader = { attrs, body ->
        if(userService.checkCurrentUserPermission('projectleader')) {
            out << body()
        }
    }
    def isHR = { attrs,body ->
        if(userService.checkCurrentUserPermission('HR')) {
            out << body()
        }
    }

}
