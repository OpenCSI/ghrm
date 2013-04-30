package com.opencsi.ghrm.controller

class ErrorController {

    def index = {
        redirect(view:'error404')
    }
    
    def error404 = {
        flash.message = "${message(code:'error.404')}" + "<br><a href=\"javascript: history.go(-1)\">${message(code:'error.404.back')}</a>"
        [projectList: ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))]
    }
}
