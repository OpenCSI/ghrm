package com.opencsi.job

import com.opencsi.ghrm.services.*
import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

import com.opencsi.ghrm.services.MailService
//import org.codehaus.groovy.tools.shell.util.MessageSource

class ScheduleReportJob {
    static triggers = {// lanch the trigger every Friday at 18:00:00
    cron name: 'scheduleReport', cronExpression: "0 0 18 ? * FRI" // s, m, h, day of month, month, day of week, (Y)
  }

    CalendarService calendarService
    ReportService reportService
    MailService mail

    def execute() {
        // For every persons
        mail = new MailService()
        def user = User.findAll()
        def weekInfos = calendarService.getWeekInfos(calendarService.getCurrentYear(),
            calendarService.getCurrentMonth(), calendarService.getCurrentDay())
        // Get for each person:
        for (u in user)
        {
            // enable to control if user made its report.
            def weekReport = []
            // init it to 0
            for (c in 0..6)
                weekReport[c] = 0 
            // the different tasks with is affected:
            def reports = reportService.findAllReportsByUserByWeek(u,weekInfos)
            for (r in reports)
            {
                // For each task, retreive the different reports for the current week
                // test them if every days have a task for the user:
                def str = (String)r.date
                def dateArray = str.split('-') // YYYY - MM - (DD + time)
                def dayArray = dateArray[2].split(' ')// DD - (time)
                for (c in 0..6)
                {
                    // Did user made its report for the day ?
                    if( (weekInfos[c]['day'].toInteger() == dayArray[0].toInteger()) &&
                        (weekInfos[0]['month'].toInteger() == dateArray[1].toInteger()) )
                        weekReport[c] = 1
                }
            }
            // send mail when reports are not update
            def message = ''
            if (weekReport[0] == 0)message += "Monday (${weekInfos[1]['day']}/${weekInfos[1]['month']} ${weekInfos[1]['year']}) : WARNING: You don't made a report for this day.\n"
            if (weekReport[1] == 0)message += "Tuesday (${weekInfos[2]['day']}/${weekInfos[2]['month']}/${weekInfos[2]['year']}) : WARNING: You don't made a report for this day.\n"
            if (weekReport[2] == 0)message += "Wednesday (${weekInfos[3]['day']}/${weekInfos[3]['month']}/${weekInfos[3]['year']}) : WARNING: You don't made a report for this day.\n"
            if (weekReport[3] == 0)message += "Thursday (${weekInfos[4]['day']}/${weekInfos[4]['month']}/${weekInfos[4]['year']}) : WARNING: You don't made a report for this day.\n"
            if (weekReport[4] == 0)message += "Friday (${weekInfos[5]['day']}/${weekInfos[5]['month']}/${weekInfos[5]['year']}) : WARNING: You don't made a report for this day."
            if (message != '')
            {
                def messageToSend = "Dear " + u.name.toString() + ",\n\n" + message + "\n\nRegards.\n\n\nInfo : this is an automatically message, do not reply it."
                println '[ScheduleReport JOB]: ' + mail.sendMail(u.email,"[GHRM] Week's Report",messageToSend)
            }
        }
    }
}
