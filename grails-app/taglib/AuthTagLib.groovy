import com.opencsi.ghrm.services.UserService

class AuthTagLib {
    def static namespace="auth"
    UserService userService

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

}
