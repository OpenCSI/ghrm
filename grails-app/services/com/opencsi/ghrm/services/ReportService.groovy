package com.opencsi.ghrm.services
import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

class ReportService {
    def CalendarService calendarService
    static transactional = true

    def ReportService() {
        calendarService = new CalendarService()
    }

    def findAllReportsByProjectByMonth(Project project, Integer year, Integer month) {
        def criteria = TaskReport.createCriteria()
        def firstDay = new DateTime(year, month, 1, 0, 0, 0, 0)
        def lastDay = new DateTime(year, month, firstDay.dayOfMonth().getMaximumValue(), 0, 0, 0, 0)
        return criteria.list {
            'between'("date", firstDay.toDate(),
                lastDay.toDate())
            'in'("task",UserTask.findAllByProject(project))
        }
    }

    def findAllReportsByUserByWeek(User user, LinkedHashMap weekInfos) {
        return findAllReportsByUserByWeek(user, weekInfos[1]['year'], weekInfos[1]['month'], weekInfos[1]['day'])
    }

    def findAllReportsByUserByWeek(User user, Integer year, Integer month, Integer day) {
        def criteria = TaskReport.createCriteria()
        def weekInfos = calendarService.getWeekInfos(year, month, day)

        def firstDayOfWeek = new DateTime(weekInfos[1]['year'],weekInfos[1]['month'], weekInfos[1]['day'], 0, 0, 0, 0)
        def lastDayOfWeek = new DateTime(weekInfos[7]['year'],weekInfos[7]['month'], weekInfos[7]['day'], 0, 0, 0, 0)
        return criteria.list {
            'between'("date", firstDayOfWeek.toDate(), lastDayOfWeek.toDate())
        }
    }
}
