package com.opencsi.ghrm.services
import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

class ReportService {

    /*
    DateTime dateMonth = new DateTime(dateCurrent.year, dateCurrent.monthOfYear, 1, 0, 0, 0, 0)

    import com.opencsi.ghrm.domain.*
    criteria = TaskReport.createCriteria()
    results = criteria.list { 'in'("task",UserTask.findAllByProject(Project.get(1))),
    between
    }
     */

    static transactional = true

    def findAllReportsByProject(Project project, Integer year, Integer month) {
        def criteria = TaskReport.createCriteria()
        def firstDay = new DateTime(year, month, 1, 0, 0, 0, 0)
        def lastDay = new DateTime(year, month, firstDay.dayOfMonth().getMaximumValue(), 0, 0, 0, 0)
        return criteria.list {
            'between'("date", firstDay.toDate(),
                              lastDay.toDate())
            'in'("task",UserTask.findAllByProject(project))
        }
    }
/*
    def findAllReportsByProject(Project project, Integer year, Integer month) {
        def criteria = TaskReport.createCriteria()
        return criteria.list {
            'between'("date", new DateTime(year, month, 1, 0, 0, 0, 0).toDate(),
                              new DateTime(year, month, new DateTime().dayOfMonth().getMaximumValue(), 0, 0, 0, 0).toDate())
            'in'("task",UserTask.findAllByProject(project))
        }
    }
*/
}
