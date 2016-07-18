package com.interlink;

import java.time.LocalDate;
import java.time.YearMonth;

public class Main {

    static CalendarFacade calendarFacade;

    public static void main(String[] args) {

        Controller controller = new Controller(YearMonth.of(2016, 7), LocalDate.now());
        controller.run();

    }
}
