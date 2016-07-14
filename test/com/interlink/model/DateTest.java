package com.interlink.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/6/16.
 */
public class DateTest {

    @Test
    public void checkFirstDayFebruary2016() {
        CalendarModel calendarModel = new CalendarModel(Month.FEBRUARY, Year.of(2016));
        List<LocalDate> localDates = calendarModel.getDates();
        LocalDate firstRealDay = localDates.get(0);
        assertThat(firstRealDay, is(LocalDate.of(2016, 2, 1)));
    }

    @Test
    public void checkLastDayFebruary2017() {
        CalendarModel calendarModel = new CalendarModel(Month.FEBRUARY, Year.of(2017));
        List<LocalDate> localDates = calendarModel.getDates();
        LocalDate firstRealDay = localDates.get(localDates.size() - 1);
        assertThat(firstRealDay, is(LocalDate.of(2017, 2, 28)));
    }
}
