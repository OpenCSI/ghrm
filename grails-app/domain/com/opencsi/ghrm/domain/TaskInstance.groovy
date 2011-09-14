package com.opencsi.ghrm.domain

class TaskInstance {

    Task task
    User user
    float days
    Date createat
    Date updateat
    Project project
    Integer status = STATUS_OPEN
    
    static final Integer STATUS_OPEN = 0
    static final Integer STATUS_CLOSE = 1

    static hasMany = [reports: TaskReport]

    static constraints = {
        days(min:0F)
        createat(nullable: true)
        updateat(nullable: true)
    }

    public getDaysUsed() {
        def c = TaskReport.createCriteria()
        return c.get {
            taskInstance {
                eq 'id', this.id
            }
            projections {
                sum('days')
            }
        }
    }
}
