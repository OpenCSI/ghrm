package com.opencsi.ghrm.domain

class UserTask {

    String name
    User user
    static hasMany = [reports:TaskReport]

    // a user task is always referenced to a project
    Project project

    // The number of hours allowed for this tasks for this user
    Integer hours = 0

    static constraints = {
        hours(min:0)
    }
}
