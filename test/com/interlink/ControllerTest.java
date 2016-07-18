package com.interlink;

/**
 * Created by Алекс on 17.07.2016.
 */

import com.interlink.model.period.PeriodForSingleMonth;
import com.interlink.model.period.PeriodForYear;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.YearMonth;

import static com.interlink.Controller.LEFT_COMMAND;
import static com.interlink.Controller.UP_COMMAND;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.spy;

public class ControllerTest {
    Controller spy;

    @Before
    public void initialize() {
        Controller controller = new Controller(YearMonth.of(2016, 4), LocalDate.of(2016, 4, 5));
        spy = spy(controller);
    }
    @Test
    public void testForSingleCommand() throws Exception {
        Mockito.doReturn(UP_COMMAND).when(spy).getCommand();
        spy.run();
        assertThat(new PeriodForYear(2016).getYearMonths(), is(spy.getMonthsPeriod().getYearMonths()));
    }

    @Test
    public void testForMultipleCommands() throws Exception {
        Mockito.doReturn(LEFT_COMMAND + LEFT_COMMAND + LEFT_COMMAND + LEFT_COMMAND).when(spy).getCommand();
        spy.run();
        assertThat(new PeriodForSingleMonth(YearMonth.of(2015, 12)).getYearMonths(), is(spy.getMonthsPeriod().getYearMonths()));
    }

    @Test
    public void testViewWithPeriodChange() throws Exception {
        Mockito.doReturn("").when(spy).getCommand();
        spy.setMonthsPeriod(new PeriodForYear(2014));
        spy.run();
        assertThat(spy.getCalendarView().generateCalendarText(() -> LocalDate.of(2016, 1, 6),
                spy.getCalendarModel().getDatesInMonths()), containsString("February 2014"));
    }
}
