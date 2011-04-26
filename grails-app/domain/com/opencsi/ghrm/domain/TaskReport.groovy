package com.opencsi.ghrm.domain

class TaskReport {

    UserTask task
    Date date
    
    // The number of hours spent on this task for date date

    // TODO: add a constraint to prevent hours > constant (working hours per days)
    Integer hours

    static constraints = {
    }
}
