package com.opencsi.ghrm.domain

class TaskReport {

    TaskInstance task
    Date date
    Integer status = TASK_STATUS_OPEN

    static final Integer TASK_STATUS_OPEN = 0
    static final Integer TASK_STATUS_VALIDATED = 1
    static final Integer TASK_STATUS_DEPRECATED = 2
    // The number of hours spent on this task for date date

    Integer hours

    static constraints = {
        hours(min:0, max:8)
    }
}
