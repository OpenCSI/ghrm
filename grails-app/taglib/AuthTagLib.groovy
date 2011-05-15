import com.opencsi.ghrm.services.UserService

class AuthTagLib {
    def static namespace="auth"
    UserService userService

    def isAdmin = { attrs, body ->
        if(userService.isUserInRole('admin')) {
            out << body()
        }
    }
}
