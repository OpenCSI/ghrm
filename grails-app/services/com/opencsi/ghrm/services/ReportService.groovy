package com.opencsi.ghrm.services
import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime
import org.apache.commons.logging.LogFactory

class ReportService {
    def CalendarService calendarService
    private static final log = LogFactory.getLog(this)
    static transactional = true
    private final String []color = ["FF0000","00FF00","0000FF","F0F000","F00F00","F000F0",
                                    "F0000F","0FF000","0F0F00","0F00F0"]
    
    String setColorProject(ArrayList<TaskReport> projectsList,TaskReport currentProject) {
        if (currentProject.taskInstance.project.color != "FFFFFF")
            return currentProject.taskInstance.project.color
        else
            return color[(int)(currentProject.taskInstance.project.id) % 10]
    }

    def ReportService() {
        calendarService = new CalendarService()
    }

    def findAllReportsByProjectByMonth(Project project, Integer year, Integer month) {
        def criteria = TaskReport.createCriteria()
        def firstDay = new DateTime(year, month, 1, 0, 0, 0, 0)
        /* By chance, I work on this project the April, 30th, which show me a bug on lastDay */
        def lastDay = (firstDay.plusMonths(1)).minusSeconds(1)

        /* Prevent a SQL error if there is no tasks associated for the current project */
        def taskInstances = TaskInstance.findAllByProject(project)
        if (taskInstances.size == 0) {
            return null
        }

        return criteria.list {
            'between'("date", firstDay.toDate(),
                lastDay.toDate())
            'in'("taskInstance", taskInstances)
            'gt'("days", 0F)
            'order'("date")
        }
    }

    def findAllReportsByUserByMonth(User user,Integer year,Integer month){
        def criteria = TaskReport.createCriteria()
        def firstDay = new DateTime(year, month, 1, 0, 0, 0, 0)
        /* By chance, I work on this project the April, 30th, which show me a bug on lastDay */
        def lastDay = (firstDay.plusMonths(1)).minusSeconds(1)

        /* Prevent a SQL error if there is no tasks associated for the current project */
        def taskInstances = TaskInstance.findAllByUser(user)
        if (taskInstances.size == 0) {
            return null
        }

        return criteria.list {
            'between'("date", firstDay.toDate(),
                lastDay.toDate())
            'in'("taskInstance", taskInstances)
            'gt'("days", 0F)
            'order'("date")
        }
    }

    def findAllReportsByUserByWeek(User user, LinkedHashMap weekInfos) {
        return findAllReportsByUserByWeek(user, weekInfos[1]['year'], weekInfos[1]['month'], weekInfos[1]['day'])
    }

    def findAllReportsByUserByWeek(User user, Integer year, Integer month, Integer day) {
        def criteria = TaskReport.createCriteria()
        def weekInfos = calendarService.getWeekInfos(year, month, day)

        /* Prevent a SQL error if there is no tasks associated for the current user */
        def tasks = TaskInstance.findAllByUser(user)
        if (tasks.size == 0)
        return null

        def firstDayOfWeek = new DateTime(weekInfos[1]['year'],weekInfos[1]['month'], weekInfos[1]['day'], 0, 0, 0, 0)
        def lastDayOfWeek = new DateTime(weekInfos[7]['year'],weekInfos[7]['month'], weekInfos[7]['day'], 0, 0, 0, 0)
        return criteria.list {
            'between'("date", firstDayOfWeek.toDate(), lastDayOfWeek.toDate())
            'in'("taskInstance", TaskInstance.findAllByUser(user))
        }
    }
}
