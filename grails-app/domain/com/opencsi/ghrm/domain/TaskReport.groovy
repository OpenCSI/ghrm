package com.opencsi.ghrm.domain

class TaskReport {

    TaskInstance taskInstance
    Date date
    Integer status = STATUS_OPEN

    static final Integer STATUS_OPEN = 0
    static final Integer STATUS_VALIDATED = 1
    static final Integer STATUS_DEPRECATED = 2
    
    // The number of hours spent on this task for date date
    Integer hours

    static constraints = {
        hours(min:0, max:8)
    }
}
