
package com.interlink;

import com.interlink.model.CalendarModel;
import com.interlink.view.CalendarView;
import com.interlink.view.CalendarViewInConsole;
import com.interlink.view.CalendarViewInHtml;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Created by employee on 7/14/16.
 */
public class CalendarFacade implements Calendar {
    private CalendarModel calendarModel;
    private CalendarView calendarView;
    private LocalDate currentDate;

    public CalendarFacade(YearMonth yearMonth, String typeOfCalendar) {
        calendarModel = new CalendarModel(yearMonth);
        switch (typeOfCalendar) {
            case "console": {
                calendarView = new CalendarViewInConsole();
                break;
            }
            case "Html": {
                calendarView = new CalendarViewInHtml();
                break;
            }
        }
    }

    public CalendarFacade(LocalDate date) {
        currentDate = date;
    }

    @Override
    public String generateCalendar(YearMonth yearMonths) {
        calendarModel = new CalendarModel(yearMonths);
        calendarView = new CalendarViewInConsole();
        return calendarView.generateCalendarText(() -> currentDate, calendarModel.getDatesInMonths());
    }

//    public String generateCalendar(Supplier<LocalDate> dateSupplier) {
//        return generateCalendar(dateSupplier.get());
//    }
}