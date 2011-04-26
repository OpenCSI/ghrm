package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.UserTask
import com.opencsi.ghrm.domain.TaskReport
import com.opencsi.ghrm.services.*
import org.joda.time.DateTime

class ProjectController {

    ReportService reportService
    CalendarService calendarService
    
    def index = {
        redirect(action: "report", params:["id":"1"])
    }

    def create = { }
    
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectInstanceList: Project.list(params), ProjectInstanceTotal: Project.count()]
    }
    
    def edit = { }
    def show = { 
        Project projectInstance = Project.findById(params.id)
        [projectInstance: projectInstance]
    }
    def save = { }

    def report = {
        def id = params.id
        def selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
        def selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()

        /* Fetch all reports related to the selected project */
        def reports = reportService.findAllReportsByProject(Project.get(id), selectedYear, selectedMonth)

        /* Create data to display */
        def calendarData = [:]
        reports.each { report ->
            def reportDay = report.date.format('dd').toInteger() - 1
            
            /* Create an empty list if not exist */
            if (!calendarData.containsKey(reportDay)) {
                calendarData[reportDay] = []
            }
            def color = calendarData[reportDay].size % 4
            calendarData[reportDay].push('<div class="color' + color + '" style="width:' + report.hours * 10 + '%" >'
                + report.task.user.initials + ': ' + report.hours +  '</div>' )
        }
        
        [projectId: id, monthInfos: calendarService.getMonthInfos(selectedYear, selectedMonth), calendarData: calendarData]
    }
}
