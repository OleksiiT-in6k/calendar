package com.interlink.model.period;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алекс on 17.07.2016.
 */
public class PeriodForYear extends MonthsPeriod {
    private MonthsPeriod previous;

    public PeriodForYear(List<YearMonth> yearMonths) {
        super(yearMonths);
    }

    public PeriodForYear(int year) {
        setYearMonths(new ArrayList<>());
        for (int i = 1; i <= 12; i++) {
            getYearMonths().add(YearMonth.of(year, i));
        }

    }

    public PeriodForYear(int year, MonthsPeriod calledFrom) {
        this(year);
        previous = calledFrom;
    }

    @Override
    public MonthsPeriod increase() {
        return this;
    }

    @Override
    public MonthsPeriod decrease() {
        if (previous == null)
            return new PeriodForSingleMonth(getYearMonths().get(0));
        else return new PeriodForSingleMonth(previous.getYearMonths().get(0));
    }

    @Override
    public MonthsPeriod next() {
        MonthsPeriod result = new PeriodForYear(getYearMonths());
        result.changeOn(12);
        return result;
    }

    @Override
    public MonthsPeriod previous() {
        MonthsPeriod result = new PeriodForYear(getYearMonths());
        result.changeOn(-12);
        return result;
    }
}
