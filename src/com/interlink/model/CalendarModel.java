package com.interlink.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

public class CalendarModel {
    private List<LocalDate> dates;
    private Month month;
    private Year year;

    public CalendarModel(Month month, Year year) {
        this.month = month;
        this.year = year;
        dates = new ArrayList<>();
        LocalDate currentDate = LocalDate.of(year.getValue(), month.getValue(), 1);
        LocalDate lastDayOfMonth = getLastDayOfMonth();
        while (!currentDate.isAfter(lastDayOfMonth)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

    }

    private LocalDate getLastDayOfMonth() {
        LocalDate date = LocalDate.of(year.getValue(), month.getValue(), 1);
        ValueRange range = date.range(ChronoField.DAY_OF_MONTH);
        int max = (int) range.getMaximum();
        LocalDate newDate = LocalDate.of(year.getValue(), month.getValue(), max);
        return newDate;
    }

    public static Boolean isWeekday(LocalDate date) {
        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return false;
        } else return true;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
