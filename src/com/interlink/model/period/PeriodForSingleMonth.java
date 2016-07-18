package com.interlink.model.period;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алекс on 17.07.2016.
 */
public class PeriodForSingleMonth extends MonthsPeriod {

    public PeriodForSingleMonth(List<YearMonth> yearMonths) {
        super(yearMonths);
    }

    public PeriodForSingleMonth(YearMonth yearMonth) {
        setYearMonths(new ArrayList<>());
        getYearMonths().add(yearMonth);
    }

    @Override
    public MonthsPeriod increase() {
        return new PeriodForYear(getYearMonths().get(0).getYear(), this);
    }

    @Override
    public MonthsPeriod decrease() {
        return this;
    }

    public MonthsPeriod next() {
        MonthsPeriod result = new PeriodForSingleMonth(getYearMonths());
        result.changeOn(1);
        return result;
    }

    public MonthsPeriod previous() {
        MonthsPeriod result = new PeriodForSingleMonth(getYearMonths());
        result.changeOn(-1);
        return result;
    }

}
