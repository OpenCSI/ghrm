package com.opencsi.ghrm.controller

import com.opencsi.ghrm.services.ProjectVirtualUserService
import com.opencsi.ghrm.services.UserService
import com.opencsi.ghrm.domain.User

class ErrorController {

    def index = {
        redirect(view:'error404')
    }
    
    def error404 = {
        flash.message = "${message(code:'error.404')}" + "<br><a href=\"javascript: history.go(-1)\">${message(code:'error.404.back')}</a>"
        [projectList: ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))]
    }
}
