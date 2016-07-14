package com.interlink.view;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.interlink.view.CalendarViewInConsole.ANSI_RED;
import static com.interlink.view.CalendarViewInConsole.ANSI_RESET;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by employee on 7/14/16.
 */
public class ConfiguredViewTest {
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
    public void testOnChangedDayOfWeeksSignature() throws Exception {
        calendarViewInConsole.setFirstDayOfWeek(DayOfWeek.TUESDAY);
        String realSignature = calendarViewInConsole.generateRowSignatures();
        assertThat(realSignature, is(" Tue Wed Thu Fri" + ANSI_RED + " Sat" + ANSI_RESET + ANSI_RED +
                " Sun" + ANSI_RESET + " Mon\n"));
    }

    @Test
    public void testOnChangedDayOfWeeks() throws Exception {
        calendarViewInConsole.setFirstDayOfWeek(DayOfWeek.TUESDAY);
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 31));
        String realSignature = calendarViewInConsole.generateDaysByDates(dates, LocalDate.of(2016, 7, 31));
        assertThat(realSignature, startsWith("               1" + ANSI_RED + "   2" + ANSI_RESET + ANSI_RED + "   3" + ANSI_RESET));
    }

    @Test
    public void testDaysOnChangedWeekends() throws Exception {
        calendarViewInConsole.setWeekends(new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY)));
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 31));
        String realDays = calendarViewInConsole.generateDaysByDates(dates, LocalDate.of(2016, 7, 31));
        assertThat(realDays, containsString(ANSI_RED + "   4" + ANSI_RESET + ANSI_RED + "   5" + ANSI_RESET));
    }

    @Test
    public void testSignatureOnChangedWeekends() throws Exception {
        calendarViewInConsole.setWeekends(new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY)));
        String realDays = calendarViewInConsole.generateRowSignatures();
        assertThat(realDays, containsString(ANSI_RED + " Mon" + ANSI_RESET + ANSI_RED + " Tue" + ANSI_RESET));
    }


}
