package com.interlink;

/**
 * Created by Алекс on 17.07.2016.
 */

import org.junit.Before;
import org.junit.Test;

import java.time.YearMonth;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ControllerTest implements Calendar {


    @Override
    public String generateCalendar(YearMonth yearMonths) {
        return yearMonths.toString() + " ";
    }

    YearMonth yearMonth;
    Controller controller;
    Command command;

    @Before
    public void initialize() {
        yearMonth = YearMonth.of(2016, 7);
        controller = new Controller(yearMonth, this);
    }

    @Test
    public void testOnRightCallForSingleMonth() throws Exception {
        command = new Command() {
            @Override
            public String getCommand() {
                return "w";
            }
        };
        controller.setCommand(command);
        assertThat(controller.run(), is("2016-01 2016-02 2016-03 2016-04 2016-05 " +
                "2016-06 2016-07 2016-08 2016-09 2016-10 2016-11 2016-12 "));
    }




}
