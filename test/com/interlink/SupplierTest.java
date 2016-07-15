package com.interlink;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by employee on 7/15/16.
 */
public class SupplierTest {
    CalendarFacade calendarFacade;

    @Before
    public void InitializeFacade() {
        calendarFacade = new CalendarFacade(Month.NOVEMBER, Year.of(2016), "console");
    }

    @Test
    public void supplierTest() throws Exception {
        String first = calendarFacade.generateCalendar(() -> LocalDate.of(2016, 11, 13));
        String second = calendarFacade.generateCalendar(() -> LocalDate.of(2016, 11, 14));
        assertThat(first, not(second));
    }
}
