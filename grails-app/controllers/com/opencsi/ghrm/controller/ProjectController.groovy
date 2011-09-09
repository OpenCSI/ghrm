package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.TaskInstance
import com.opencsi.ghrm.domain.TaskReport
import com.opencsi.ghrm.domain.Customer
import com.opencsi.ghrm.services.*
import org.joda.time.DateTime

class ProjectController {

    ReportService reportService
    CalendarService calendarService
    TaskService taskService

    def create = {
    }
    
    def save = {
        def project = new Project()
        project.name = params.name
        project.label = params.label
        project.status = Project.STATUS_OPEN
        project.description = params.description
        project.customer = Customer.get(params.customer.toInteger())

        if(project.save(onFailError: true, flush:true)) {
            redirect(action: 'list')
        } else {
            render(view:'create', model: [projectInstance: project])
        }
    }

    def show = {
        def project = Project.get(params.id)
        def tasks = TaskInstance.findAllByProject(project)

        [project: project, projectTasks: tasks]
    }
    
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectInstanceList: Project.list(params), ProjectInstanceTotal: Project.count()]
    }
        
    def report = {
        def id = params.id
        def selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
        def selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
        def nameMonth = ['Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Décembre']
        
        /* Fetch all reports related to the selected project */
        def reports = reportService.findAllReportsByProjectByMonth(Project.get(id), selectedYear, selectedMonth)

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
                    'htmldata':'<div class="color' + color + '" style="width:' + report.hours * 10 + '%" >' + '<span class="entry" style="width:100%">' + report.taskInstance.user.initials + ': ' + report.hours + '</span></div>',
                    'tooltipdata': 'User: ' + report.taskInstance.user.name + '<br/>Task: ' + report.taskInstance.task.name
            ])
        }

        def projectInfo = "<ul><li><A href=''></a></li></ul>"
        
        [projectId: id, 
            monthInfos: calendarService.getMonthInfos(selectedYear, selectedMonth),
            calendarData: calendarData,
            extraInfo: projectInfo,
            nameMonth: nameMonth,
            currentYear: selectedYear,
            currentMonth: selectedMonth
        ]
    }
}
