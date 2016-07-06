package com.interlink.view;

import com.interlink.model.CalendarModel;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;


public class CalendarView {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String LOCALE="en";

    public String output="";

    private static void printInColor(String text, String ansiColor){
        System.out.print(ansiColor);
        System.out.printf("%4s", text);
        System.out.print(ANSI_RESET);
    }

    private static void printDayInRightColor(LocalDate date) {
        if(CalendarModel.isToday(date)){
            printInColor(date.getDayOfMonth()+"", ANSI_BLUE);
        }
            else
        if (!CalendarModel.isWeekday(date)) {
            printInColor(date.getDayOfMonth()+"", ANSI_RED);
        } else System.out.printf("%4d", date.getDayOfMonth());
    }

    public static void printRowSignatures() {
        System.out.printf("%1s", "");
        for (DayOfWeek day=DayOfWeek.MONDAY;day.getValue()<=5;day=day.plus(1)) {
            System.out.printf("%4s", day.getDisplayName(TextStyle.SHORT,new Locale(LOCALE)));
        }
        for (DayOfWeek day=DayOfWeek.SATURDAY;day.getValue()!=1;day=day.plus(1)){
            System.out.print(ANSI_RED);
            System.out.printf("%4s", day.getDisplayName(TextStyle.SHORT,new Locale(LOCALE)));
            System.out.print(ANSI_RESET);
        }
        System.out.println();
    }

    public static void printTitleByDate(Month month, Year year){
        System.out.println(month.getDisplayName(TextStyle.FULL,new Locale(LOCALE))+" "+year.getValue());
    }

    public static void printDaysByDates(List<LocalDate>dates){
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        for (int i = 0; i < dates.size(); ) {
            LocalDate date = dates.get(i);
            if (dayOfWeek.equals(date.getDayOfWeek())) {
                printDayInRightColor(date);
                i++;
            } else System.out.printf("%4s", "");
            if (dayOfWeek.equals(dayOfWeek.SUNDAY)) {
                dayOfWeek = DayOfWeek.MONDAY;
                System.out.println();
            } else dayOfWeek = dayOfWeek.plus(1);
        }
    }

}
