package com.interlink.view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 7/13/16.
 */
public class CalendarViewInHtml extends AbstractCalendarView {
    public static final Tag BLUE_TAG = new Tag("<td class=\"today\">", "</td>");
    public static final Tag RED_TAG = new Tag("<td class=\"weekend\">", "</td>");
    public static final Tag UNCOLORED_TAG = new Tag("<td>", "</td>");
    public static final Tag ROW_TAG = new Tag("<tr>", "</tr>");
    public static final Tag CELL_TAG = new Tag("<td>", "</td>");
    public static final Tag TABLE_TAG = new Tag("<table>", "</table>");
    public static final Tag START_AND_END_OF_HTML_PAGE = new Tag(
            "<html>\n" +
                    "<head>\n" +
                    "<title>Calendar</title>\n" +
                    "<link href=\"calendar.css\" rel=\"stylesheet\" type=\"text/css\">" +
                    "</head>\n" +
                    "<body>",
            "</body>\n" +
                    "</html>");


    @Override
    protected String getEmphasizedToday(LocalDate date) {
        return getDayWithColor(date, BLUE_TAG);
    }

    @Override
    protected String getEmphasizedWeekEnd(LocalDate date) {
        return getDayWithColor(date, RED_TAG);
    }

    @Override
    protected String getEmphasizedWeekDay(LocalDate date) {
        return getDayWithColor(date, UNCOLORED_TAG);
    }

    private String getDayWithColor(LocalDate date, Tag tag) {
        return getStringWithTags(date.getDayOfMonth() + "", tag);
    }

    @Override
    protected String getEmphasizedWeekEndSignature(DayOfWeek dayOfWeek) {
        String displayName = dayOfWeek.getDisplayName(TextStyle.SHORT, new Locale(LOCALE));
        return getStringWithTags(displayName, RED_TAG);
    }

    @Override
    protected String getEmphasizedWeekDaySignature(DayOfWeek dayOfWeek) {
        String displayName = dayOfWeek.getDisplayName(TextStyle.SHORT, new Locale(LOCALE));
        return getStringWithTags(displayName, UNCOLORED_TAG);
    }


    @Override
    protected Tag getStartAndEndOfWeekTag() {
        return ROW_TAG;
    }

    @Override
    protected Tag getStartAndEndOfTableCalendarTag() {
        return TABLE_TAG;
    }

    @Override
    protected String getEmptyCell() {
        return getStringWithTags(String.format("%3s", ""), CELL_TAG);
    }

    @Override
    protected Tag getStartAndEndOfCalendarTag() {
        return START_AND_END_OF_HTML_PAGE;
    }

}
