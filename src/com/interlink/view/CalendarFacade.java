package com.interlink.view;

import com.interlink.model.CalendarModel;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

/**
 * Created by employee on 7/14/16.
 */
public class CalendarFacade {
    private CalendarModel calendarModel;
    private AbstractCalendarView calendarView;

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
        return calendarView.generateCalendarText(date, calendarModel.getDates());
    }
}
