package com.interlink;

import com.interlink.model.CalendarModel;
import com.interlink.view.CalendarView;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class Main {

    public static void main(String[] args) {//Parameter should be a name of the month(all letters are capital), like FEBRUARY, or empty.
        Year year = Year.now();
        Month month = LocalDate.now().getMonth();
        if (!(args.length == 0)) {
            month = Month.valueOf(args[0]);
        }
        CalendarView.printCalendar(LocalDate.now(), new CalendarModel(month, year).getDates());
    }
}
