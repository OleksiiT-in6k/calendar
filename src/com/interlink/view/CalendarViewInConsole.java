package com.interlink.view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;


public class CalendarViewInConsole extends AbstractCalendarView {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final Tag RED_TAG = new Tag(ANSI_RED, ANSI_RESET);
    public static final Tag BLUE_TAG = new Tag(ANSI_BLUE, ANSI_RESET);
    public static final Tag UNCOLORED_TAG = new Tag("", "");
    public static final Tag START_AND_END_OF_LINE_TAG = new Tag("", "\n");
    public static final Tag START_AND_END_OF_CALENDAR_TAG = new Tag("", "");
    public static final String STRING_FORMAT = "%4s";


    @Override
    protected String getEmphasizedToday(LocalDate date) {
        return getDayWithColor(date, BLUE_TAG);
    }

    @Override
    protected String getEmphasizedWeekEnd(LocalDate date) {
        return getDayWithColor(date, RED_TAG);
    }

    @Override
    protected String getEmphasizedWeekDay(LocalDate date) {
        return getDayWithColor(date, UNCOLORED_TAG);
    }

    private String getDayWithColor(LocalDate date, Tag tag) {
        String formattedDayOfMonth = String.format(STRING_FORMAT, date.getDayOfMonth());
        return getStringWithTags(formattedDayOfMonth, tag);
    }

    @Override
    protected String getEmphasizedWeekEndSignature(DayOfWeek dayOfWeek) {
        return getColoredDayOfWeek(dayOfWeek, RED_TAG);
    }

    @Override
    protected String getEmphasizedWeekDaySignature(DayOfWeek dayOfWeek) {
        return getColoredDayOfWeek(dayOfWeek, UNCOLORED_TAG);
    }

    private String getColoredDayOfWeek(DayOfWeek dayOfWeek, Tag tag) {
        return (getStringWithTags(
                String.format(STRING_FORMAT, dayOfWeek.getDisplayName(TextStyle.SHORT, new Locale(LOCALE))) + "",
                tag));
    }

    @Override
    protected Tag getStartAndEndOfWeekTag() {
        return START_AND_END_OF_LINE_TAG;
    }

    @Override
    protected Tag getStartAndEndOfTableCalendarTag() {
        return Tag.EMPTY_TAG;
    }


    @Override
    protected String getEmptyCell() {
        return getStringWithTags(String.format(STRING_FORMAT, ""), UNCOLORED_TAG);
    }

    @Override
    protected Tag getStartAndEndOfCalendarTag() {
        return START_AND_END_OF_CALENDAR_TAG;
    }

}
