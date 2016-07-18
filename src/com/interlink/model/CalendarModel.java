package com.interlink.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

public class CalendarModel {
    private List<LocalDate> dates;
    private List<List<LocalDate>> datesInMonths;


    public CalendarModel(YearMonth yearMonth) {
        dates = new ArrayList<>();
        addDatesForYearMonth(yearMonth);
        datesInMonths = splitIntoMonths();
    }

    public CalendarModel(List<YearMonth> yearMonths) {
        dates = new ArrayList<>();
        for (YearMonth yearMonth : yearMonths)
            addDatesForYearMonth(yearMonth);
        datesInMonths = splitIntoMonths();
    }

    public CalendarModel(int Year) {
        dates = new ArrayList<>();
        for (int i = 1; i <= 12; i++)
            addDatesForYearMonth(YearMonth.of(Year, Month.of(i)));
    }


    private void addDatesForYearMonth(YearMonth yearMonth) {
        int year = yearMonth.getYear();
        Month month = yearMonth.getMonth();
        LocalDate currentDate = LocalDate.of(year, month.getValue(), 1);
        LocalDate lastDayOfMonth = getLastDayOfMonth(yearMonth);
        while (!currentDate.isAfter(lastDayOfMonth)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
    }

    protected List<List<LocalDate>> splitIntoMonths() {
        List<List<LocalDate>> result = new ArrayList<>();
        Month currentMonth = dates.get(0).getMonth();
        List monthDays = new ArrayList<>();
        for (LocalDate date : dates) {
            monthDays.add(date);
            if (!(date.getMonth().equals(currentMonth))) {
                result.add(new ArrayList<>(monthDays));
                monthDays = new ArrayList<>();
                currentMonth = date.getMonth();
            }
        }
        result.add(new ArrayList<>(monthDays));
        return result;
    }

    private LocalDate getLastDayOfMonth(YearMonth yearMonth) {
        int year = yearMonth.getYear();
        Month month = yearMonth.getMonth();
        LocalDate date = LocalDate.of(year, month.getValue(), 1);
        ValueRange range = date.range(ChronoField.DAY_OF_MONTH);
        int max = (int) range.getMaximum();
        LocalDate newDate = LocalDate.of(year, month.getValue(), max);
        return newDate;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public List<List<LocalDate>> getDatesInMonths() {
        return datesInMonths;
    }

    public void setDatesInMonths(List<List<LocalDate>> datesInMonths) {
        this.datesInMonths = datesInMonths;
    }
}
