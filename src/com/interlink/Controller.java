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
    CalendarModel calendarModel;
    CalendarView calendarView;
    LocalDate localDate;
    MonthsPeriod monthsPeriod;

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
            case "w": {
                monthsPeriod = monthsPeriod.increase();
                break;
            }
            case "s": {
                monthsPeriod = monthsPeriod.decrease();
                break;
            }
            case "a": {
                monthsPeriod = monthsPeriod.previous();
                break;
            }
            case "d": {
                monthsPeriod = monthsPeriod.next();
                break;
            }
        }
    }

}
