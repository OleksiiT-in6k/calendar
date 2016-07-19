package com.interlink;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Алекс on 18.07.2016.
 */
public class AggregatorTest {
    List<String> calendarViews;
    Aggregator aggregator;

    public void initializeSingleMonth() {
        calendarViews = new ArrayList<>();
        calendarViews.add("January 2016\n" +
                " Mon Tue Wed Thu Fri Sat Sun\n" +
                "                   1   2   3\n" +
                "   4   5   6   7   8   9  10\n" +
                "  11  12  13  14  15  16  17\n" +
                "  18  19  20  21  22  23  24\n" +
                "  25  26  27  28  29  30  31");
    }

    public void initializeTwelveMonth() {
        calendarViews = new ArrayList<>();
        calendarViews.add("January 2016\n" + "1 2 3\n");
        calendarViews.add("February 2016\n" + "1 2 3\n");
        calendarViews.add("March 2016\n" + "1 2 3\n");
        calendarViews.add("April 2016\n" + "1 2 3\n");
        calendarViews.add("May 2016\n" + "1 2 3 4\n");
        calendarViews.add("June 2016\n" + "1 2 3 4\n");
        calendarViews.add("July 2016\n" + "1 2 3 4\n");
        calendarViews.add("August 2016\n" + "1 2 3 4\n");
        calendarViews.add("September 2016\n" + "1 2 3 4 5\n");
        calendarViews.add("October 2016\n" + "1 2 3 4 5\n");
        calendarViews.add("November 2016\n" + "1 2 3 4 5\n");
        calendarViews.add("December 2016\n" + "1 2 3 4 5\n");

    }

    @Test
    public void testForSingleMonth() throws Exception {
        initializeSingleMonth();
        aggregator = new Aggregator(3, 4);
        assertThat(aggregator.generateFormattedString(calendarViews), is(calendarViews.get(0)));
    }

    @Test
    public void testForTwelveMonthsInSingleRow() throws Exception {
        initializeTwelveMonth();
        aggregator = new Aggregator(1, 12);
        assertThat(aggregator.generateFormattedString(calendarViews), is("January 2016February 2016  March 2016 " +
                " April 2016    May 2016   June 2016   July 2016 August 2016September 2016October 2016November 2016December 2016\n" +
                "       1 2 3       1 2 3       1 2 3       1 2 3     1 2 3 4     1 2 3 4     1 2 3 4     1 2 3 4   1 2 3 4 5   1 2 3 4 5   1 2 3 4 5   1 2 3 4 5\n"));
    }
}
