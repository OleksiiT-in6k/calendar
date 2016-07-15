package com.interlink;

import com.interlink.model.CalendarModel;
import com.interlink.view.AbstractCalendarView;
import com.interlink.view.CalendarViewInConsole;
import com.interlink.view.CalendarViewInHtml;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.function.Supplier;

/**
 * Created by employee on 7/14/16.
 */
public class CalendarFacade {
    private CalendarModel calendarModel;
    private AbstractCalendarView calendarView;
    private LocalDate currentDate;

    public CalendarFacade(Month month, Year year, String typeOfCalendar) {
        calendarModel = new CalendarModel(month, year);
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

    public String generateCalendar(LocalDate date) {
        return calendarView.generateCalendarText(() -> date, calendarModel.getDates());
    }

    public String generateCalendar(Supplier<LocalDate> dateSupplier) {
        return generateCalendar(dateSupplier.get());
    }
}
