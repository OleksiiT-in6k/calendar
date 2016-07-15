package com.interlink;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class Main {

    static CalendarFacade calendarFacade;

    public static void main(String[] args) {
        Year year = Year.now();
        Month month = LocalDate.now().getMonth();
        String secondParameter;
        secondParameter = args[1];
        calendarFacade = new CalendarFacade(month, year, "console");
        switch (secondParameter) {
            case "console": {
                printInConsole(calendarFacade.generateCalendar(LocalDate::now));
                break;
            }
            case "Html": {
                printInHtml(calendarFacade.generateCalendar(LocalDate::now));
                break;
            }

        }

    }

    protected static void printInConsole(String text) {
        System.out.println(text);
    }


    protected static void printInHtml(String text) {
        try {
            BufferedWriter writeToHtml = new BufferedWriter(new FileWriter("calendar.html"));
            writeToHtml.write(text);
            writeToHtml.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
