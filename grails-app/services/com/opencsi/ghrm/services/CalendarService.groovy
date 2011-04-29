package com.opencsi.ghrm.services
import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

class CalendarService {

    static transactional = true

    def getNameOfDays(){
        def data = [1: "Lundi",
            2: "Mardi",
            3: "Mercredi",
            4: "Jeudi",
            5: "Vendredi",
            6: "Samedi",
            7: "Dimanche" ]

        return data
    }

    def getCurrentMonth() {
        return new DateTime().monthOfYear().get()
    }

    def getCurrentYear() {
        return new DateTime().year().get()
    }

    def getCurrentDay() {
        return new DateTime().dayOfMonth().get()
    }

    /* Return a collection of week's days for the given date */
    def getWeekInfos(Integer year, Integer month, Integer day) {
        /* It's probably pretty ugly */
        DateTime selectedDate = new DateTime(year, month, day, 0, 0, 0, 0)

        def data = [:]
        def currentDayOfWeek = selectedDate.dayOfWeek().get()
        def i

        for(i=0;i<currentDayOfWeek+1;i++) {
            def currentDate = selectedDate.minusDays(currentDayOfWeek-i)
            data[i] = [year: currentDate.year().get(),
                month: currentDate.monthOfYear().get(),
                day: currentDate.dayOfMonth().get(),
            ]
        }

        for(i=currentDayOfWeek;i<9;i++) {
            def currentDate = selectedDate.plusDays(i-currentDayOfWeek)
            data[i] = [year: currentDate.year().get(),
                month: currentDate.monthOfYear().get(),
                day: currentDate.dayOfMonth().get(),
            ]
        }

        return data
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
