package com.interlink;

import com.interlink.model.period.MonthsPeriod;
import com.interlink.model.period.PeriodForSingleMonth;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алекс on 17.07.2016.
 */
public class Controller {
    public static final String UP_COMMAND = "w";
    public static final String DOWN_COMMAND = "s";
    public static final String LEFT_COMMAND = "a";
    public static final String RIGHT_COMMAND = "d";


    private Calendar calendar;
    private MonthsPeriod monthsPeriod;
    private Command command;


    Controller(YearMonth yearMonth, Calendar calendar) {
        this.calendar = calendar;
        monthsPeriod = new PeriodForSingleMonth(yearMonth);
        command = new CommandConsoleImpl();
    }

    public String getStartedCalendar() {
        String result = "";
        result += generateCalendarForPeriod();
        return result;
    }

    public String run() {
        String result = "";
        runCommand(command.getCommand());
        result += generateCalendarForPeriod();
        return result;
    }

    protected String generateCalendarForPeriod() {
        String result = "";
        List<String> calendarViews = new ArrayList<>();
        for (YearMonth yearMonth : monthsPeriod.getYearMonths()) {
            calendarViews.add(calendar.generateCalendar(yearMonth));
        }
        Aggregator aggregator = new Aggregator(4, 3);
        result = aggregator.generateFormattedString(calendarViews);
        return result;
    }


    protected void runCommand(String command) {
        for (int i = 0; i < command.length(); i++) {
            runSingleCommand(command.substring(i, i + 1));
        }
    }

    protected void runSingleCommand(String singleCommand) {
        switch (singleCommand) {
            case UP_COMMAND: {
                monthsPeriod = monthsPeriod.increase();
                break;
            }
            case DOWN_COMMAND: {
                monthsPeriod = monthsPeriod.decrease();
                break;
            }
            case LEFT_COMMAND: {
                monthsPeriod = monthsPeriod.previous();
                break;
            }
            case RIGHT_COMMAND: {
                monthsPeriod = monthsPeriod.next();
                break;
            }
        }
    }

    protected MonthsPeriod getMonthsPeriod() {
        return monthsPeriod;
    }

    protected void setMonthsPeriod(MonthsPeriod monthsPeriod) {
        this.monthsPeriod = monthsPeriod;
    }

    public void setCommand(Command command) {
        this.command = command;
    }


}
