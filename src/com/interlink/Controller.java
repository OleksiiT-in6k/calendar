package com.interlink;

import com.interlink.model.period.MonthsPeriod;
import com.interlink.model.period.PeriodForSingleMonth;

import java.time.YearMonth;
import java.util.Scanner;

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
        command = new CommandImpl();
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
        for (YearMonth yearMonth : monthsPeriod.getYearMonths()) {
            result += calendar.generateCalendar(yearMonth);
        }
        return result;
    }

    protected String getCommand() {
        String command = "";
        Scanner scanIn = new Scanner(System.in);
        command = scanIn.nextLine();
        scanIn.close();
        return command;
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
