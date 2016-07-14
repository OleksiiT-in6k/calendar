package com.interlink.view;

/**
 * Created by employee on 7/13/16.
 */

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HtmlViewTest {
    private List<LocalDate> generateDateList(LocalDate startDate, LocalDate finishDate) {
        List<LocalDate> dates = new ArrayList<>();
        for (LocalDate date = startDate; date.isBefore(finishDate.plusDays(1)); date = date.plusDays(1))
            dates.add(date);
        return dates;
    }

    private CalendarViewInHtml calendarViewInHtml;

    @Before
    public void initialize() {
        calendarViewInHtml = new CalendarViewInHtml();
    }

    @Test
    public void titleTest() throws Exception {
        String realTitle = calendarViewInHtml.generateTitleByDate(Month.FEBRUARY, 2014);
        assertThat(realTitle, is("February 2014"));
    }

    @Test
    public void signatureTest() throws Exception {
        String realSignature = calendarViewInHtml.generateRowSignatures();
        assertThat(realSignature, is("<tr><td>Mon</td>" +
                "<td>Tue</td>" +
                "<td>Wed</td>" +
                "<td>Thu</td>" +
                "<td>Fri</td>" +
                "<td class=\"weekend\">Sat</td>" +
                "<td class=\"weekend\">Sun</td></tr>"));
    }


    @Test
    public void firstDayInTableTest() throws Exception {
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 31));
        String realFirstDay = calendarViewInHtml.generateDaysByDates(dates, LocalDate.of(2016, 7, 14));
        assertThat(realFirstDay, startsWith("<td>   </td><td>   </td><td>   </td><td>   </td><td>1</td>"));
    }

    @Test
    public void checkStartOfHTMLStructure() throws Exception {
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 31));
        String realStartOfDocument = calendarViewInHtml.generateCalendarText(LocalDate.of(2016, 7, 14), dates);
        assertThat(realStartOfDocument, startsWith("<html>\n" +
                "<head>\n" +
                "<title>Calendar</title>\n" +
                "<link href=\"calendar.css\" rel=\"stylesheet\" type=\"text/css\"></head>\n" +
                "<body>"));
    }

    @Test
    public void checkEndOfHTMLStructure() throws Exception {
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 31));
        String realStartOfDocument = calendarViewInHtml.generateCalendarText(LocalDate.of(2016, 7, 14), dates);
        assertThat(realStartOfDocument, endsWith("</body>\n" +
                "</html>"));
    }

    @Test
    public void daysTestOnRightWeekendsColor() throws Exception {
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 31));
        String realDays = calendarViewInHtml.generateDaysByDates(dates, LocalDate.of(2016, 7, 28)
        );
        assertThat(realDays, allOf(containsString(("<td class=\"weekend\">2</td>")),
                containsString("<td class=\"weekend\">3</td>"))
        );
    }
}
