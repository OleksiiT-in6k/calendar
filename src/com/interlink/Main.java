package com.interlink;

import com.interlink.model.CalendarModel;
import com.interlink.view.CalendarFacade;
import com.interlink.view.CalendarViewInConsole;
import com.interlink.view.CalendarViewInHtml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class Main {


    public static void main(String[] args) {
        Year year = Year.now();
        Month month = LocalDate.now().getMonth();
//        if (!(args.length == 0)) {
//            month = Month.valueOf(args[0]);
//        }
//        CalendarModel calendarModel = new CalendarModel(month, year);
//        if (args[1].equals("console")) {
//            printInConsole(calendarModel);
//        }
//        if (args[1].equals("html"))
//            printInHtml(calendarModel);

        CalendarFacade calendarFacade = new CalendarFacade(month, year, "Html");
        System.out.println(calendarFacade.generateCalendar(LocalDate.now()));
    }

    private static void printInConsole(CalendarModel calendarModel) {
        CalendarViewInConsole calendarViewInConsole = new CalendarViewInConsole();
        System.out.println(calendarViewInConsole.generateCalendarText(LocalDate.now(), calendarModel.getDates()));
    }

    private static void printInHtml(CalendarModel calendarModel) {
        CalendarViewInHtml calendarViewInHtml = new CalendarViewInHtml();
        String calendarHtml = calendarViewInHtml.generateCalendarText(LocalDate.now(), calendarModel.getDates());
        try {
            BufferedWriter writeToHtml = new BufferedWriter(new FileWriter("calendar.html"));
            writeToHtml.write(calendarHtml);
            writeToHtml.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
