package com.opencsi.ghrm.domain

class TaskReport {

    UserTask task
    Date date
    Integer status = TASK_STATUS_CREATE

    static final Integer TASK_STATUS_CREATE = 0
    static final Integer TASK_STATUS_VALIDATED = 1
    static final Integer TASK_STATUS_DEPRECATED = 2
    // The number of hours spent on this task for date date

    // TODO: add a constraint to prevent hours > constant (working hours per days)
    Integer hours

    static constraints = {
    }
}
