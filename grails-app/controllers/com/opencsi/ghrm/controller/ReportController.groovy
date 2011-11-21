package com.opencsi.ghrm.controller
import com.opencsi.ghrm.services.*
import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

class ReportController {

    CalendarService calendarService
    UserService userService
    ReportService reportService
    TaskInstanceService taskInstanceService

    private def nameMonth = [g.message(code:'month.1'),g.message(code:'month.2'),g.message(code:'month.3'),
            g.message(code:'month.4'),g.message(code:'month.5'),g.message(code:'month.6'),g.message(code:'month.7'),
            g.message(code:'month.8'),g.message(code:'month.9'),g.message(code:'month.10'),g.message(code:'month.11')
            ,g.message(code:'month.12')]
    private def daysWeek = [g.message(code:"day.1"),g.message(code:"day.2"),g.message(code:"day.3"),
                    g.message(code:"day.4"),g.message(code:"day.5"),g.message(code:"day.6"),
                    g.message(code:"day.7")]

    def create = {
        def selectedYear,selectedMonth,selectedDay,weekInfos
        try{
            selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
            selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
            selectedDay = params.day?params.day.toInteger(): calendarService.getCurrentDay()
            weekInfos = calendarService.getWeekInfos(selectedYear, selectedMonth, selectedDay)
        }catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
            redirect(action:'week')
        }
        
        def taskLists = taskInstanceService.findAllOpenByUser(User.findByUid(userService.getAuthenticatedUserName()))
        def taskSelectOptions = []

        taskLists.each { taskInstance ->
            taskSelectOptions.push(id: taskInstance.id, label: taskInstance.project.label + ':' + taskInstance.task.label)
        }

        [taskSelectOptions: taskSelectOptions, weekInfos: weekInfos,day: daysWeek,
            projectList: Project.list(), create:true]
    }

    def week = {
        def selectedYear,selectedMonth,selectedDay,weekInfos
        try{
            selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
            selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
            selectedDay = params.day?params.day.toInteger(): calendarService.getCurrentDay()
            weekInfos = calendarService.getWeekInfos(selectedYear, selectedMonth, selectedDay)
        }catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
            redirect(action:'month')
        }

        def calendarData = [:]
                
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
                'htmldata': '<div class="color' + color + '" style="width:'+ 99 + '%" >' + '<span class="entry" style="width:100%">' + report.taskInstance.user.initials + ': ' + report.days + '</span></div>',
                'tooltipdata': "${message(code:'global.project')}: " + report.taskInstance.project.name + "<br/>${message(code:'global.task')}: " + report.taskInstance.task.name,
                    'id' : report.id
                ])
        }
        [calendarData: calendarData, weekInfos: weekInfos,day: daysWeek,
            projectList: Project.list(), create:false]
    }

    def month = {
        def id,selectedYear,selectedMonth,reports
        try{
            id = params.id
            selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
            selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
            /* Fetch all reports related to the selected project */
            reports = reportService.findAllReportsByUserByMonth(User.findByUid(userService.getAuthenticatedUserName()),
                                                selectedYear, selectedMonth)
        }catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
            redirect(action:'month')
        }
        /* Create data to display */
        def calendarData = [:]

        reports.each { report ->
            def reportDay = report.date.format('dd').toInteger() - 1

            /* Create an empty list if not exist */
            if (!calendarData.containsKey(reportDay)) {
                calendarData[reportDay] = []
            }
            def color = (calendarData[reportDay]['htmldata']).size() % 4
            calendarData[reportDay].push([
                    'htmldata':'<div class="color' + color + '" style="width:' + 99 + '%" >' + '<span class="entry" style="width:100%">' + report.taskInstance.user.initials + ': ' + report.days + '</span></div>',
                    'tooltipdata': "${message(code:'global.project')}: " + report.taskInstance.project.name + "<br/>${message(code:'global.task')}: " + report.taskInstance.task.name
            ])
        }
        
        def projectInfo = ''//"<ul><li><A href=''></a></li></ul>"

        [projectId: id,
            monthInfos: calendarService.getMonthInfos(selectedYear, selectedMonth),
            calendarData: calendarData,
            extraInfo: projectInfo,
            nameMonth: nameMonth,
            currentYear: selectedYear,
            currentMonth: selectedMonth,
            value : 'report',
            projectList: Project.list()
        ]
    }

    def save = {
        if (params.taskInstance != null)
        {
            def firstDay = new DateTime(params.firstDate.Year.toInteger(), params.firstDate.Month.toInteger(), params.firstDate.Day.toInteger(), 0, 0, 0, 0)
            params.days.each { day, value ->
                try{
                    if(value.toFloat() > 0.0) {
                        new TaskReport(
                            taskInstance: TaskInstance.get(params.taskInstance.toInteger()),
                            date: firstDay.plusDays(day.toInteger()).toDate(),
                            days: value.toFloat()
                        ).save(failOnError: true)
                        flash.message = "${message(code:'report.save.succes')}"
                    }
                }catch(Exception e)
                {
                    flash.message = "${message(code:'report.save.error')}"           
                }
            }
        }else
            flash.message = "${message(code : 'report.create.error')}"
        redirect(controller:'report', action:'week')
    }
    
    def confirm = {
        [id : params.id]
    }

    def delete = {
        def report = TaskReport.get(params.id)
        if (report)
        {
            report.delete()
            flash.message = "${message(code:'report.delete')}"
        }else
            flash.message = "${message(code:'report.delete.error')}"
        redirect(action: "week")
    }
}
