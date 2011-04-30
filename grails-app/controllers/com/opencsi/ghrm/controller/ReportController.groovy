package com.opencsi.ghrm.controller
import com.opencsi.ghrm.services.*
import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

class ReportController {

    CalendarService calendarService
    UserService userService
    ReportService reportService

    def create = {
        def selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
        def selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
        def selectedDay = params.day?params.day.toInteger(): calendarService.getCurrentDay()
        def calendarData = [:]
        def weekInfos = calendarService.getWeekInfos(selectedYear, selectedMonth, selectedDay)


        [weekInfos: weekInfos]
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
            calendarData[dayOfWeek].push(
                '<div class="color' + color + '" style="width:' + report.hours * 10 + '%" >' +
                '<span class="entry" style="width:100%">' + report.task.user.initials + ': ' + report.hours
                + '</span></div>' )

        }
        [calendarData: calendarData, weekInfos: weekInfos]
    }
}
