package com.interlink.model.period;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алекс on 17.07.2016.
 */
public abstract class MonthsPeriod implements PeriodInterface {
    private List<YearMonth> yearMonths;

    public MonthsPeriod(List<YearMonth> yearMonths) {
        this.yearMonths = yearMonths;
    }

    public MonthsPeriod() {
        yearMonths = new ArrayList<>();
    }


    protected void changeOn(int i) {
        List<YearMonth> newYearMonths = new ArrayList<>();
        for (YearMonth yearMonth : yearMonths)
            newYearMonths.add(yearMonth.plusMonths(i));
        yearMonths = newYearMonths;
    }

    public List<YearMonth> getYearMonths() {
        return yearMonths;
    }

    public void setYearMonths(List<YearMonth> yearMonths) {
        this.yearMonths = yearMonths;
    }

}
