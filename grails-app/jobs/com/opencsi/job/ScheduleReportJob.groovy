package com.opencsi.job

import com.opencsi.ghrm.services.*
import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime


class ScheduleReportJob {
    //def timeout = 5000l // execute job once in 5 seconds
    static triggers = {// lanch the trigger every Friday at 18:00:00 (we don't care of the day of any month)
    cron name: 'scheduleReport', cronExpression: "0 0 10 ? * 5" // s, m, h, day of month, month, day of week, (Y)
  }

    CalendarService calendarService
    UserService userService
    ReportService reportService
    TaskInstanceService taskInstanceService

    def execute() {
        // execute task
        print "coucou!"
        // For every persons:
        // Get for each person:
            // the different tasks with is affected:
                // For each task, retreive the different reports for the current week
                // send mail when reports are not update
    }
}
