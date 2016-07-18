package com.interlink;

import com.interlink.model.CalendarModel;
import com.interlink.model.period.MonthsPeriod;
import com.interlink.model.period.PeriodForSingleMonth;
import com.interlink.view.CalendarView;
import com.interlink.view.CalendarViewInConsole;

import java.time.LocalDate;
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


    private CalendarModel calendarModel;


    private CalendarView calendarView;
    private LocalDate localDate;
    private MonthsPeriod monthsPeriod;

    Controller(YearMonth yearMonth, LocalDate localDate) {
        calendarModel = new CalendarModel(yearMonth);
        calendarView = new CalendarViewInConsole();
        monthsPeriod = new PeriodForSingleMonth(yearMonth);
        this.localDate = localDate;
    }

    public void run() {
        System.out.println(calendarView.generateCalendarText(LocalDate::now, calendarModel.getDatesInMonths()));
        runCommand(getCommand());
        calendarModel = new CalendarModel(monthsPeriod.getYearMonths());
        System.out.println(calendarView.generateCalendarText(LocalDate::now, calendarModel.getDatesInMonths()));
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

    protected CalendarView getCalendarView() {
        return calendarView;
    }

    protected CalendarModel getCalendarModel() {
        return calendarModel;
    }

    protected void setCalendarModel(CalendarModel calendarModel) {
        this.calendarModel = calendarModel;
    }
}
