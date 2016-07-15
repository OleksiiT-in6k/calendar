package com.interlink.interactive;

import org.junit.Test;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by employee on 7/15/16.
 */
public class MonthPeriodTest {
    MonthPeriod monthPeriod;

    public void initializeSingleMonth() {
        List<YearMonth> yearMonths = new ArrayList<>();
        yearMonths.add(YearMonth.of(2016, 7));
        monthPeriod = new MonthPeriod(yearMonths);
    }

    public void initializeTwelveMonthOnYear() {
        List<YearMonth> yearMonths = new ArrayList<>();
        for (int i = 1; i <= 12; i++)
            yearMonths.add(YearMonth.of(2016, i));
        monthPeriod = new MonthPeriod(yearMonths);
    }

    @Test
    public void testNextMonth() throws Exception {
        initializeSingleMonth();
        assertThat(monthPeriod.next().getYearMonths().get(0), is(YearMonth.of(2016, 8)));
    }

    @Test
    public void testNextYear() throws Exception {
        initializeTwelveMonthOnYear();
        assertThat(monthPeriod.next().getYearMonths().get(5), is(YearMonth.of(2017, 6)));
    }

    @Test
    public void testPreviousMonth() throws Exception {
        initializeSingleMonth();
        assertThat(monthPeriod.previous().getYearMonths().get(0), is(YearMonth.of(2016, 6)));
    }

    @Test
    public void testPreviousYear() throws Exception {
        initializeTwelveMonthOnYear();
        assertThat(monthPeriod.previous().getYearMonths().get(5), is(YearMonth.of(2015, 6)));
    }

    @Test
    public void testIncreaseOnSingleMonth() throws Exception {
        initializeSingleMonth();
        assertThat(monthPeriod.increase().getYearMonths().get(1), is(YearMonth.of(2016, 2)));
    }

    @Test
    public void testIncreaseOnYear() throws Exception {
        initializeTwelveMonthOnYear();
        assertThat(monthPeriod.increase().getYearMonths(), is(monthPeriod.getYearMonths()));
    }

    @Test
    public void testDecreaseOnYear() throws Exception {
        initializeTwelveMonthOnYear();
        assertThat(monthPeriod.decrease().getYearMonths().get(0), is(YearMonth.of(2016, 1)));

    }

    @Test
    public void testDecreaseOnMonth() throws Exception {
        initializeSingleMonth();
        assertThat(monthPeriod.decrease().getYearMonths(), is(monthPeriod.getYearMonths()));

    }
}
