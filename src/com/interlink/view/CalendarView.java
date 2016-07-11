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
    public static final String LOCALE = "en";

    private static String printInColor(String text, String ansiColor) {
        String outPut = "";
        outPut += ansiColor;
        outPut += String.format("%4s", text);
        outPut += ANSI_RESET;
        return outPut;
    }

    private static String printDayInRightColor(LocalDate date,LocalDate today) {
        String outputDay="";
        if (date.equals(today)) {
            outputDay += printInColor(date.getDayOfMonth() + "", ANSI_BLUE);
        } else if (!CalendarModel.isWeekday(date)) {
            outputDay += printInColor(date.getDayOfMonth() + "", ANSI_RED);
        } else outputDay += String.format("%4s", date.getDayOfMonth());
        return outputDay;
    }

    public static String generateRowSignatures() {
        String outputMonthes="";
        outputMonthes += String.format("%1s", "");
        for (DayOfWeek day = DayOfWeek.MONDAY; day.getValue() <= 5; day = day.plus(1)) {
            outputMonthes += String.format("%4s", day.getDisplayName(TextStyle.SHORT, new Locale(LOCALE)));
        }
        for (DayOfWeek day = DayOfWeek.SATURDAY; day.getValue() != 1; day = day.plus(1)) {
            outputMonthes += ANSI_RED;
            outputMonthes += String.format("%4s", day.getDisplayName(TextStyle.SHORT, new Locale(LOCALE)));
            outputMonthes += ANSI_RESET;
        }
        return outputMonthes;
    }

    public static String generateTitleByDate(Month month, int year) {
        String outputMonthAndYear = month.getDisplayName(TextStyle.FULL, new Locale(LOCALE)) + " " + year;
        return outputMonthAndYear;
    }

    public static String generateDaysByDates(List<LocalDate> dates, LocalDate today) {
        String outputDays="";
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        for (int i = 0; i < dates.size(); ) {
            LocalDate date = dates.get(i);
            if (dayOfWeek.equals(date.getDayOfWeek())) {
                outputDays+=printDayInRightColor(date,today);
                i++;
            } else
                outputDays += String.format("%4s", "");
            if (dayOfWeek.equals(dayOfWeek.SUNDAY)) {
                dayOfWeek = DayOfWeek.MONDAY;
                outputDays += "\n";
            } else dayOfWeek = dayOfWeek.plus(1);
        }
        return outputDays;
    }

    public static void printCalendar(LocalDate localDate, List<LocalDate> dates) {
        System.out.println(generateTitleByDate(localDate.getMonth(),localDate.getYear()));
        System.out.println(generateRowSignatures());
        System.out.println(generateDaysByDates(dates,localDate));
    }

}
