package com.opencsi.ghrm.controller

import com.opencsi.ghrm.services.*
import com.opencsi.ghrm.domain.*

import org.joda.time.DateTime
import java.util.Calendar

class ReportController {

    CalendarService calendarService
    UserService userService
    ReportService reportService
    TaskInstanceService taskInstanceService
    def pdfRenderingService 

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
            taskSelectOptions.push(id: taskInstance.id, label: taskInstance.project.customer.name + ':' +taskInstance.project.label + ':' + taskInstance.task.label)
        }

        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        def daysWeek = [g.message(code:"day.1"),g.message(code:"day.2"),g.message(code:"day.3"),
                    g.message(code:"day.4"),g.message(code:"day.5"),g.message(code:"day.6"),
                    g.message(code:"day.7")]
        def listProject = ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        
        [taskSelectOptions: taskSelectOptions, weekInfos: weekInfos,day: daysWeek,
            projectList: listProject, create:true]
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
            
            def color = reportService.setColorProject(reports,report)
            calendarData[dayOfWeek].push([
                'htmldata': '<div style="background-color:#'+ color +';border: 1px solid '+ color +'" style="width:'+ 99 + '%" >' + '<span class="entry" style="width:100%">' + report.taskInstance.user.initials + ': ' + report.days + '</span></div>',
                'tooltipdata': report.taskInstance.project.customer.name + "<br>${message(code:'global.project')}: " + report.taskInstance.project.name + "<br/>${message(code:'global.task')}: " + report.taskInstance.task.name,
                    'id' : report.id
                ])
        }

        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        def daysWeek = [g.message(code:"day.1"),g.message(code:"day.2"),g.message(code:"day.3"),
                    g.message(code:"day.4"),g.message(code:"day.5"),g.message(code:"day.6"),
                    g.message(code:"day.7")]
        def listProject = ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        
        [calendarData: calendarData, weekInfos: weekInfos,day: daysWeek,
            projectList: listProject, create:false]
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
            def color = reportService.setColorProject(reports,report)
            calendarData[reportDay].push([
                    'htmldata':'<div style="background-color:#'+ color +';border: 1px solid '+ color +';" style="width:' + 99 + '%" >' + '<span class="entry" style="width:100%">' + report.taskInstance.user.initials + ': ' + report.days + '</span></div>',
                    'tooltipdata': report.taskInstance.project.customer.name + "<br>${message(code:'global.project')}: " + report.taskInstance.project.name + "<br/>${message(code:'global.task')}: " + report.taskInstance.task.name
            ])
        }
        
        def projectInfo = ''//"<ul><li><A href=''></a></li></ul>"
        def Tasks = taskInstanceService.findAllOpenByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
		def nameMonth = [g.message(code:'month.1'),g.message(code:'month.2'),g.message(code:'month.3'),
            g.message(code:'month.4'),g.message(code:'month.5'),g.message(code:'month.6'),g.message(code:'month.7'),
            g.message(code:'month.8'),g.message(code:'month.9'),g.message(code:'month.10'),g.message(code:'month.11'),
            g.message(code:'month.12')]
        def listProject = ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        
        [projectId: id,
            monthInfos: calendarService.getMonthInfos(selectedYear, selectedMonth),
            calendarData: calendarData,
            extraInfo: projectInfo,
            nameMonth: nameMonth,
            currentYear: selectedYear,
            currentMonth: selectedMonth,
            value : 'report',
            projectList: listProject
        ]
    }

    def save = {
        if (params.taskInstance != null)
        {
            // Test if the project is closed or not:
            def project = TaskInstance.get(params.taskInstance.toInteger()).project
            if (project.status == Project.STATUS_OPEN)
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
                flash.message = "${message(code : 'report.create.error.closed')}"
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
    
    def export = {
        def TasksInstanceList = TaskInstance.findAllByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        def clients = []
        TasksInstanceList.each { TInstance ->
            clients.push(id: TInstance.project.customer.id, label: TInstance.project.customer.name)
        }
        
        def tasksReport = TaskReport.findAll()
        Integer firstYear = (tasksReport[0].date.getYear() + 1900)
        Integer lastYear  = (tasksReport[tasksReport.size()-1].date.getYear() + 1900)
        def nameMonth = []
        for(int i=1;i<13;i++){
            nameMonth.push(id: i-1,name: g.message(code:'month.' + i))
        }
        Calendar c = Calendar.getInstance()
        def numberWeeks = c.getActualMaximum(Calendar.WEEK_OF_YEAR)
        def listProject = ProjectVirtualUserService.getByUser(User.findByUid(UserService.getAuthenticatedUserNameStatic()))
        
        [client: new HashSet(clients),fYear: firstYear,lYear: lastYear,
         numberWeeks: numberWeeks,nameMonth: nameMonth,params: params,
         projectList: listProject]
    }
    
    def exportPDF = {
        reportService = new ReportService()
        String CName,picture,typePicture
        def list = []
        def strDate = "" // Text date period
        def Infos
        def calendarData = [:]
        boolean noValue = true
        if(params?.clientInstance){
            def client = Customer.get(params.clientInstance)
            picture = client.picture.encodeBase64()
            typePicture = client.typePicture
            def projectsList = Project.findAllByCustomer(client)
            def user = User.findByUid(UserService.getAuthenticatedUserNameStatic())
            CName = client.name
            Calendar calBegin = Calendar.getInstance()
            Calendar calEnd = Calendar.getInstance()
            if (params?.date == "week"){
                int year = Integer.valueOf(params.yearWeek)
                int week = Integer.valueOf(params.weekWeek)
                ////////////////////////////////////////////////////////
                calBegin.setFirstDayOfWeek(Calendar.MONDAY)
                calBegin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
                calBegin.set(Calendar.WEEK_OF_YEAR,week)
                calBegin.set(Calendar.YEAR,year)
                calBegin.set(Calendar.HOUR_OF_DAY, 0)
                calBegin.set(Calendar.MINUTE, 0)
                calBegin.set(Calendar.SECOND, 0)
                ////////////////////////////////////////////////////////
                calEnd.setFirstDayOfWeek(Calendar.SUNDAY)
                calEnd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
                calEnd.set(Calendar.WEEK_OF_YEAR,week)
                calEnd.set(Calendar.YEAR,year)
                calEnd.add(Calendar.DATE,7)
                calEnd.set(Calendar.HOUR_OF_DAY, 23)
                calEnd.set(Calendar.MINUTE, 59)
                calEnd.set(Calendar.SECOND, 59)
                ////////////////////////////////////////////////////////  
                strDate = g.message(code:"global.date.from") + " " 
                strDate += (calBegin.getTime().getMonth()+1) + "/" + calBegin.getTime().getDate() + "/" + (calBegin.getTime().getYear() + 1900)
                strDate += " " + g.message(code:"global.date.to") + " "
                strDate += (calEnd.getTime().getMonth()+1) + "/" + calEnd.getTime().getDate() + "/" + (calEnd.getTime().getYear() + 1900)
                ///////////////////////////////////////////////////////
                Infos = calendarService.getWeekInfos(calBegin.getTime().getYear()+1900, calBegin.getTime().getMonth()+1,calBegin.getTime().getDate())
            }
            else if (params?.date == "month"){
                int year = Integer.valueOf(params.yearMonth)
                int month = Integer.valueOf(params.monthMonth)
                calBegin.set(year,month,1,0,0,0)
                strDate = g.message(code:'month.' + (month+1)) + " " + year
                /////////////////////////////////////////////////////////
                calEnd.set(year,month,calBegin.getActualMaximum(Calendar.DAY_OF_MONTH),0,0,0)
                /////////////////////////////////////////////////////////
                Infos = calendarService.getMonthInfos(year, month+1)
            }else{
                flash.message = "${message(code:'report.export.error.typeDate')}"
                redirect(action: "export")
            }
            def tasksReport = TaskReport.findAllByDateBetween(calBegin.getTime(),calEnd.getTime())

            projectsList.each{ PList ->
                ProjectPDFVirtual pPDF = new ProjectPDFVirtual()
                tasksReport.each{ report->
                    if (report.taskInstance.project == PList && report.taskInstance.user == user){
                        def numDay = (params.date == "week")?(new DateTime(report.date)).dayOfWeek().get():(new DateTime(report.date)).dayOfMonth().get()-1
                        pPDF.addTask(numDay,report.days)
                        pPDF.setColor(reportService.setColorProject(tasksReport,report))
                        noValue = false
                    }
                }
                ////////////////////////////////
                for(int i=1;i<calEnd.getTime().getDate()-calBegin.getTime().getDate()+2;i++)
                    if(!pPDF.data[i])
                        pPDF.data[i] = []
                pPDF.setPName(PList.name)
                list.push(pPDF)
            }
        }else{
            flash.message = "${message(code:'report.export.error.client')}"
            redirect(action: "export")
        }
        if (list.isEmpty() || noValue){
            flash.message = "${message(code:'report.export.error.noProject')}"
            redirect(action: "export")
        }
        def args
        if(params.date == "week"){
            args = [template:"exportPDFWeek", model:[client:CName,list: list,date:strDate,weekInfos:Infos,picture:picture, typePicture: typePicture]]
        }else if (params.date == "month"){
            args = [template:"exportPDFMonth", model:[client:CName,list: list,date:strDate,monthInfos:Infos,picture:picture, typePicture: typePicture]]
        }
        pdfRenderingService.render(args+[controller:this],response)
    }
}
