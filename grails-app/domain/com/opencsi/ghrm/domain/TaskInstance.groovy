package com.opencsi.ghrm.domain

class TaskInstance {

    Task task
    User user
    Integer hours
    Date createat
    Date updateat
    Project project

    static hasMany = [reports: TaskReport]

    static constraints = {
        hours(min:0)
        createat(nullable: true)
        updateat(nullable: true)
    }
}
