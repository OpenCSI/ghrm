package com.opencsi.ghrm.controller
import com.opencsi.ghrm.services.*
import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

class ReportController {

    CalendarService calendarService
    UserService userService
    ReportService reportService
    TaskInstanceService taskInstanceService

    def create = {
        def selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
        def selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
        def selectedDay = params.day?params.day.toInteger(): calendarService.getCurrentDay()
        def weekInfos = calendarService.getWeekInfos(selectedYear, selectedMonth, selectedDay)

        def taskLists = taskInstanceService.findAllOpenByUser(User.findByUid(userService.getAuthenticatedUserName()))
        def taskSelectOptions = []

        taskLists.each { taskInstance ->
            taskSelectOptions.push(id: taskInstance.id, label: taskInstance.project.label + ':' + taskInstance.task.label)
        }

        [taskSelectOptions: taskSelectOptions, weekInfos: weekInfos]
    }

    def week = {
        def selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
        def selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
        def selectedDay = params.day?params.day.toInteger(): calendarService.getCurrentDay()

        def calendarData = [:]
        def weekInfos = calendarService.getWeekInfos(selectedYear, selectedMonth, selectedDay)

        def reports = reportService.findAllReportsByUserByWeek(
            User.findByUid(userService.getAuthenticatedUserName()),
            weekInfos
        )

        reports.each { report ->
            def dayOfReport = new DateTime(report.date)
            def dayOfWeek = dayOfReport.dayOfWeek().get()
 
            if (!calendarData.containsKey(dayOfWeek)) {
                calendarData[dayOfWeek] = []
            }
            
            def color = calendarData[dayOfWeek].size % 4
            calendarData[dayOfWeek].push([
                'htmldata': '<div class="color' + color + '" style="width:' + report.hours * 10 + '%" >' + '<span class="entry" style="width:100%">' + report.taskInstance.user.initials + ': ' + report.hours + '</span></div>',
                'tooltipdata': 'Project: ' + report.taskInstance.project.name + '<br/>Task: ' + report.taskInstance.task.name
                ])

        }
        [calendarData: calendarData, weekInfos: weekInfos]
    }

    def save = {
        def firstDay = new DateTime(params.firstDate.Year.toInteger(), params.firstDate.Month.toInteger(), params.firstDate.Day.toInteger(), 0, 0, 0, 0)
        params.days.each { day, value ->
            if(value.toInteger() > 0) {
                new TaskReport(
                    taskInstance: TaskInstance.get(params.taskInstance.toInteger()),
                    date: firstDay.plusDays(day.toInteger()).toDate(),
                    hours: value.toInteger()
                ).save(failOnError: true)
            }
        }

        redirect(controller:'report', action:'week')
    }
}
