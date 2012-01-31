package com.opencsi.ghrm.controller

import com.opencsi.ghrm.domain.Project
import com.opencsi.ghrm.domain.TaskInstance
import com.opencsi.ghrm.domain.TaskReport
import com.opencsi.ghrm.domain.Customer
import com.opencsi.ghrm.domain.User

import com.opencsi.ghrm.services.*

import org.joda.time.DateTime

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ProjectController {

    ReportService reportService
    CalendarService calendarService
    TaskService taskService
    TaskInstanceService taskInstanceService
    // Export service provided by Export plugin
    def exportService

    private def nameMonth = [g.message(code:'month.1'),g.message(code:'month.2'),g.message(code:'month.3'),
            g.message(code:'month.4'),g.message(code:'month.5'),g.message(code:'month.6'),g.message(code:'month.7'),
            g.message(code:'month.8'),g.message(code:'month.9'),g.message(code:'month.10'),g.message(code:'month.11')
            ,g.message(code:'month.12')]

    def create = {
        [projectList: Project.list()]
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
        def project
        try{
            project = Project.get(params.id)
        }catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
            redirect(action:'list')
        }
        def tasks = TaskInstance.findAllByProject(project)

        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        [project: project, projectTasks: tasks,projectList: Tasks.project]
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
        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        [projectInstanceList: Project.list(params), ProjectInstanceTotal: Project.count(),projectList: Tasks.project]
    }

    def modify = {
        def projectInstance
        try{
            projectInstance = Project.get(params.id)
            ///////////////////////////////////////////
            if (params.modify)
            {
                projectInstance.name = params.name
                projectInstance.label = params.label
                projectInstance.description = params.description
                if (params.status == null)
                {
                    projectInstance.status = 1
                    def taskInstance = TaskInstance.findByProject(projectInstance)
                    taskInstance.each{
                        it.status = 1
                    }
                }
                else
                {
                    projectInstance.status = 0
                    def taskInstance = TaskInstance.findByProject(projectInstance)
                    taskInstance.each{
                        it.status = 0
                    }
                }
                projectInstance.save(flush:true)
                flash.message = "${message(code:'project.modify')}"
                redirect(action:'list')
            }
        }catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
            redirect(action:'list')
        }
        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        def state = projectInstance.status == 0 ? "true" : "false"
        [id: params.id,name: projectInstance.name,label: projectInstance.label,
            description: projectInstance.description, projectList: Tasks.project,state:state]
    }
        
    def report = {
        def reports,id,selectedYear,selectedMonth
        try{
            id = params.id
            selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
            selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
            /* Fetch all reports related to the selected project */
            reports = reportService.findAllReportsByProjectByMonth(Project.get(id), selectedYear, selectedMonth)
        }
        catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
            redirect(action:'list')
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
                    'tooltipdata': report.taskInstance.project.customer.name + "<br>${message(code:'global.user')}: " + report.taskInstance.user.name + "<br/>${message(code:'global.task')}: " + report.taskInstance.task.name
            ])
        }

        def projectInfo = ''//"<ul><li><A href=''></a></li></ul>"
        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        
        [projectId: id, 
            monthInfos: calendarService.getMonthInfos(selectedYear, selectedMonth),
            calendarData: calendarData,
            extraInfo: projectInfo,
            nameMonth: nameMonth,
            currentYear: selectedYear,
            currentMonth: selectedMonth,
            value : 'project',
            projectList: Tasks.project,
            nameProject : Project.get(id).name
        ]
    }

    def reportPDF = {
        def reports,id,selectedYear,selectedMonth
        try{
            id = params.id
            selectedYear = params.year?params.year.toInteger(): calendarService.getCurrentYear()
            selectedMonth = params.month?params.month.toInteger(): calendarService.getCurrentMonth()
            /* Fetch all reports related to the selected project */
            reports = reportService.findAllReportsByProjectByMonth(Project.get(id), selectedYear, selectedMonth)
        }
        catch(Exception e)
        {
            flash.message = "${message(code:'global.error.open')}"
            redirect(action:'list')
        }

        // Extract in PDF File:
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
