package com.interlink.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/6/16.
 */
public class DateTest {

    @Test
    public void checkFirstDayFebruary2016() {
        CalendarModel calendarModel = new CalendarModel(YearMonth.of(2016, 2));
        List<LocalDate> localDates = calendarModel.getDates();
        LocalDate firstRealDay = localDates.get(0);
        assertThat(firstRealDay, is(LocalDate.of(2016, 2, 1)));
    }

    @Test
    public void checkLastDayFebruary2017() {
        CalendarModel calendarModel = new CalendarModel(YearMonth.of(2017, 2));
        List<LocalDate> localDates = calendarModel.getDates();
        LocalDate firstRealDay = localDates.get(localDates.size() - 1);
        assertThat(firstRealDay, is(LocalDate.of(2017, 2, 28)));
    }

    @Test
    public void checkListLengthForYear() throws Exception {
        CalendarModel calendarModel = new CalendarModel(2016);
        List<LocalDate> localDates = calendarModel.getDates();
        assertThat(localDates.size(), is(366));

    }
}
