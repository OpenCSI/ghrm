package com.opencsi.ghrm.services
import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

class CalendarService {

    static transactional = true

    def getCurrentMonth() {
        return new DateTime().monthOfYear().get()
    }

    def getCurrentYear() {
        return new DateTime().year().get()
    }

    /* A small service to help construct calendar */
    def getMonthInfos(Integer year, Integer month) {
        def data = [:]
        DateTime date = new DateTime(year, month, 1, 0, 0, 0, 0)

        data['numberOfDays']  = date.dayOfMonth().getMaximumValue()
        data['firstDayOfWeek'] = date.dayOfWeek().get()

        if (date.monthOfYear().get() == 1) {
            data['previousYear'] = date.year().get() - 1
            data['previousMonth'] = 12
        } else {
            data['previousYear'] = date.year().get()
            data['previousMonth'] = date.monthOfYear().get() - 1
        }

        if (date.monthOfYear().get() == 12) {
            data['nextYear'] = date.year().get() + 1
            data['nextMonth'] = 1
        } else {
            data['nextYear'] = date.year().get()
            data['nextMonth'] = date.monthOfYear().get() + 1
        }

        return data
    }
}
