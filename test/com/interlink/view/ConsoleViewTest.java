package com.interlink.view;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.interlink.view.CalendarViewInConsole.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by employee on 7/6/16.
 */
public class ConsoleViewTest {
    private List<LocalDate> generateDateList(LocalDate startDate, LocalDate finishDate) {
        List<LocalDate> dates = new ArrayList<>();
        for (LocalDate date = startDate; date.isBefore(finishDate.plusDays(1)); date = date.plusDays(1))
            dates.add(date);
        return dates;
    }

    private CalendarViewInConsole calendarViewInConsole;

    @Before
    public void initialize() {
        calendarViewInConsole = new CalendarViewInConsole();
    }

    @Test
    public void titleTest() {
        String realTitle = calendarViewInConsole.generateTitleByDate(Month.FEBRUARY, 2014);
        assertThat(realTitle, CoreMatchers.is("February 2014"));
    }

    @Test
    public void signatureTest() {
        String realSignature = calendarViewInConsole.generateRowSignatures();
        assertThat(realSignature, CoreMatchers.is(" Mon Tue Wed Thu Fri" + ANSI_RED + " Sat" + ANSI_RESET + ANSI_RED +
                " Sun" + ANSI_RESET + "\n"));
    }

    @Test
    public void daysTestOnRightEnters() {
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 5, 1), LocalDate.of(2016, 5, 31));
        String realDays = calendarViewInConsole.generateDaysByDates(dates, LocalDate.of(2016, 2, 1));
        assertThat(realDays, CoreMatchers.allOf(
                CoreMatchers.containsString("\n   2"),
                CoreMatchers.containsString("\n   9"),
                CoreMatchers.containsString("\n  16"),
                CoreMatchers.containsString("\n  23"),
                CoreMatchers.containsString("\n  30")
        ));

    }

    @Test
    public void daysTestOnRightWeekendsColor() {
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 31));
        String realDays = calendarViewInConsole.generateDaysByDates(dates, LocalDate.of(2016, 7, 28)
        );
        assertThat(realDays, CoreMatchers.allOf(CoreMatchers.containsString((ANSI_RED + "   " + 2 + ANSI_RESET)),
                CoreMatchers.containsString(ANSI_RED + "   " + 3 + ANSI_RESET))
        );
    }

    @Test
    public void daysTestOnRightTodayDay() {
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 31));
        String realDays = calendarViewInConsole.generateDaysByDates(dates, LocalDate.of(2016, 7, 28));
        assertThat(realDays, CoreMatchers.containsString(ANSI_BLUE + "  " + 28 + ANSI_RESET));
    }

}
