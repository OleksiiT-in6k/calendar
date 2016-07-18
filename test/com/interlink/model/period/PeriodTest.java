package com.interlink.model.period;

/**
 * Created by Алекс on 17.07.2016.
 */

import org.junit.Test;

import java.time.YearMonth;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PeriodTest {
    MonthsPeriod monthsPeriod;

    private void initializeSingleMonth() {
        monthsPeriod = new PeriodForSingleMonth(YearMonth.of(2016, 7));
    }

    private void initializeYear() {
        monthsPeriod = new PeriodForYear(2016);
    }

    @Test
    public void testNextOnSingleMonth() throws Exception {
        initializeSingleMonth();
        assertThat(monthsPeriod.next().getYearMonths().get(0), is(YearMonth.of(2016, 8)));
    }

    @Test
    public void testPreviousOnSingleMonth() throws Exception {
        initializeSingleMonth();
        assertThat(monthsPeriod.previous().getYearMonths().get(0), is(YearMonth.of(2016, 6)));
    }

    @Test
    public void testNextOnYear() throws Exception {
        initializeYear();
        assertThat(monthsPeriod.next().getYearMonths().get(11), is(YearMonth.of(2017, 12)));
    }

    @Test
    public void testPreviousOnYear() throws Exception {
        initializeYear();
        assertThat(monthsPeriod.previous().getYearMonths().get(11), is(YearMonth.of(2015, 12)));
    }

    @Test
    public void testIncreaseOnMonth() throws Exception {
        initializeSingleMonth();
        assertThat(monthsPeriod.increase().getYearMonths().get(11), is(YearMonth.of(2016, 12)));
    }

    @Test
    public void testDecreaseOnMonth() throws Exception {
        initializeSingleMonth();
        assertThat(monthsPeriod.decrease(), is(monthsPeriod));
    }

    @Test
    public void testIncreaseOnYear() throws Exception {
        initializeYear();
        assertThat(monthsPeriod.increase(), is(monthsPeriod));
    }

    @Test
    public void testDecreaseOnYear() throws Exception {
        initializeYear();
        assertThat(monthsPeriod.decrease().getYearMonths().size(), is(1));
    }

    @Test
    public void testIncreaseDecreaseOnMonth() throws Exception {
        initializeSingleMonth();
        assertThat(monthsPeriod.increase().decrease().getYearMonths().get(0), is(YearMonth.of(2016, 7)));
    }

    @Test
    public void testIncreaseNextPreviousDecreaseOnMonth() throws Exception {
        initializeSingleMonth();
        assertThat(monthsPeriod.increase().next().previous().decrease().getYearMonths().get(0), is(YearMonth.of(2016, 1)));
    }
}
