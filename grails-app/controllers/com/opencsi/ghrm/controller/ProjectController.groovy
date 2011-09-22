package com.opencsi.ghrm.controller
import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.TaskInstance
import com.opencsi.ghrm.domain.TaskReport
import com.opencsi.ghrm.domain.Customer
import com.opencsi.ghrm.services.*
import org.joda.time.DateTime
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ProjectController {

    ReportService reportService
    CalendarService calendarService
    TaskService taskService
    // Export service provided by Export plugin
    def exportService

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
        // Export :
         if(params?.format && params.format != "html")
         {
            response.contentType = ConfigurationHolder.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=task.list.${params.extension}")
            List fields = ['name','createat','customer.name','description','code','label']
            Map labels = ['name' : 'Name','createat' : 'Create At', 'Customer.name' : 'Customer',
                            'description': 'Description','label': 'Label']
            exportService.export(params.format, response.outputStream,Project.list(params), fields, labels,[:],[:])
         }
        [projectInstanceList: Project.list(params), ProjectInstanceTotal: Project.count()]
    }
        
    def report = {
        def id = params.id
        def selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
        def selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
        def nameMonth = [g.message(code:'month.1'),g.message(code:'month.2'),g.message(code:'month.3'),
            g.message(code:'month.4'),g.message(code:'month.5'),g.message(code:'month.6'),g.message(code:'month.7'),
            g.message(code:'month.8'),g.message(code:'month.9'),g.message(code:'month.10'),g.message(code:'month.11')
            ,g.message(code:'month.12')]
        
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
                    'htmldata':'<div class="color' + color + '" style="width:' + 99 + '%" >' + '<span class="entry" style="width:100%">' + report.taskInstance.user.initials + ': ' + report.days + '</span></div>',
                    'tooltipdata': 'User: ' + report.taskInstance.user.name + '<br/>Task: ' + report.taskInstance.task.name
            ])
        }

        def projectInfo = ''//"<ul><li><A href=''></a></li></ul>"
        
        [projectId: id, 
            monthInfos: calendarService.getMonthInfos(selectedYear, selectedMonth),
            calendarData: calendarData,
            extraInfo: projectInfo,
            nameMonth: nameMonth,
            currentYear: selectedYear,
            currentMonth: selectedMonth
        ]
    }

    def reportPDF = {
        def id = params.id
        def selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
        def selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
        def nameMonth = [g.message(code:'month.1'),g.message(code:'month.2'),g.message(code:'month.3'),
            g.message(code:'month.4'),g.message(code:'month.5'),g.message(code:'month.6'),g.message(code:'month.7'),
            g.message(code:'month.8'),g.message(code:'month.9'),g.message(code:'month.10'),g.message(code:'month.11')
            ,g.message(code:'month.12')]

        /* Fetch all reports related to the selected project */
        def reports = reportService.findAllReportsByProjectByMonth(Project.get(id), selectedYear, selectedMonth)

        // Extract to PDF File:
         if(params?.format && params.format != "html")
         {
            response.contentType = ConfigurationHolder.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=Calendar-${nameMonth[selectedMonth-1]}-${selectedYear}.${params.extension}")
            List fields=['date','days','taskInstance.user.name','taskInstance.project.name',
                        'taskInstance.task.name']
            Map labels = ['date' : 'Date','days' : 'Report Days','taskInstance.user.name' : 'User' ,
                        'taskInstance.project.name' : 'Project','taskInstance.task.name' : 'Task']
            exportService.export(params.format, response.outputStream,reports, fields,labels, [:],[:])
         }
        // End Extract
        // Redirect to the calendar view:
        redirect(action:'report',params:[id:id,year:selectedYear,month:selectedMonth])
    }
}
