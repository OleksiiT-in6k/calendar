package com.interlink.view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by employee on 7/12/16.
 */
public abstract class AbstractCalendarView implements CalendarViewConfig {
    public static final String LOCALE = "en";
    private DayOfWeek firstDayOfWeek;
    private List<DayOfWeek> weekends;

    public AbstractCalendarView() {
        firstDayOfWeek = DayOfWeek.MONDAY;
        weekends = new ArrayList<>();
        weekends.add(DayOfWeek.SATURDAY);
        weekends.add(DayOfWeek.SUNDAY);
    }

    public AbstractCalendarView(DayOfWeek firstDayOfWeek) {
        this();
        setFirstDayOfWeek(firstDayOfWeek);
    }

    @Override
    public void setFirstDayOfWeek(DayOfWeek firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    @Override
    public void setWeekends(List<DayOfWeek> weekends) {
        this.weekends = weekends;
    }

    public String generateCalendarText(LocalDate localDate, List<LocalDate> dates) {
        String result = "";
        result += getStartAndEndOfCalendarTag().getOpenedTag();
        result += getStartAndEndOfTableCalendarTag().getOpenedTag();
        result += generateTitleByDate(dates.get(0).getMonth(), dates.get(0).getYear()) + "\n";
        result += generateRowSignatures();
        result += generateDaysByDates(dates, localDate) + "\n";
        result += getStartAndEndOfTableCalendarTag().getOpenedTag();
        result += getStartAndEndOfCalendarTag().getClosedTag();
        return result;
    }

    protected String getStringWithTags(String text, Tag tag) {
        String outPut = tag.getOpenedTag() + text + tag.getClosedTag();
        return outPut;
    }

    protected String generateTitleByDate(Month month, int year) {
        String outputMonthAndYear = month.getDisplayName(TextStyle.FULL, new Locale(LOCALE)) + " " + year;
        return outputMonthAndYear;
    }

    protected String generateRowSignatures() {
        String outputMonths = getStartAndEndOfWeekTag().getOpenedTag();
        DayOfWeek currentDayOfWeek = firstDayOfWeek;
        for (int i = 0; i < 7; i++) {
            if (weekends.contains(currentDayOfWeek))
                outputMonths += getEmphasizedWeekEndSignature(currentDayOfWeek);
            else outputMonths += getEmphasizedWeekDaySignature(currentDayOfWeek);
            currentDayOfWeek = currentDayOfWeek.plus(1);
        }
        outputMonths += getStartAndEndOfWeekTag().getClosedTag();
        return outputMonths;
    }


    protected String generateDaysByDates(List<LocalDate> dates, LocalDate today) {
        String outputDays = "";
        DayOfWeek dayOfWeek = firstDayOfWeek;
        for (int i = 0; i < dates.size(); ) {
            LocalDate date = dates.get(i);
            if (dayOfWeek.equals(date.getDayOfWeek())) {
                outputDays += printDayInRightColor(date, today);
                i++;
            } else
                outputDays += getEmptyCell();
            if (dayOfWeek.equals(firstDayOfWeek.minus(1))) {
                dayOfWeek = firstDayOfWeek;
                outputDays += getStartAndEndOfWeekTag().getClosedTag();
                outputDays += getStartAndEndOfWeekTag().getOpenedTag();
            } else dayOfWeek = dayOfWeek.plus(1);
        }
        return outputDays;
    }

    private String printDayInRightColor(LocalDate date, LocalDate today) {
        String outputDay = "";
        if (date.equals(today)) {
            outputDay += getEmphasizedToday(date);
        } else if (isWeekend(date)) {
            outputDay += getEmphasizedWeekEnd(date);
        } else outputDay += getEmphasizedWeekDay(date);
        return outputDay;
    }

    private boolean isWeekend(LocalDate date) {
        return weekends.contains(date.getDayOfWeek());
    }


    protected abstract String getEmphasizedToday(LocalDate date);

    protected abstract String getEmphasizedWeekEnd(LocalDate date);

    protected abstract String getEmphasizedWeekDay(LocalDate date);

    protected abstract String getEmphasizedWeekEndSignature(DayOfWeek dayOfWeek);

    protected abstract String getEmphasizedWeekDaySignature(DayOfWeek dayOfWeek);

    protected abstract Tag getStartAndEndOfWeekTag();

    protected abstract Tag getStartAndEndOfTableCalendarTag();

    protected abstract String getEmptyCell();

    protected abstract Tag getStartAndEndOfCalendarTag();

}
