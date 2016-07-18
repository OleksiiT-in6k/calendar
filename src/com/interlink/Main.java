package com.interlink;

import java.time.LocalDate;
import java.time.YearMonth;

public class Main {

    static CalendarFacade calendarFacade;

    public static void main(String[] args) {
        Calendar calendar = new CalendarFacade(LocalDate.now());
        Controller controller = new Controller(YearMonth.of(2016, 7), calendar);
        System.out.println(controller.getStartedCalendar());
        System.out.println(controller.run());
    }
}
