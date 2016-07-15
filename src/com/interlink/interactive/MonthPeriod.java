package com.interlink.interactive;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 7/15/16.
 */
public class MonthPeriod {
    static private final int[] LENGTHS_OF_PERIOD = {1, 12};
    private List<YearMonth> yearMonths;
    private int currentStep;
    int[] periodLengths = LENGTHS_OF_PERIOD;

    MonthPeriod() {

    }

    MonthPeriod(List<YearMonth> yearMonths) {
        this.yearMonths = yearMonths;
        currentStep = yearMonths.size();
    }

    public MonthPeriod next() {
        return new MonthPeriod(getMovedPeriod(1));
    }

    public MonthPeriod previous() {
        return new MonthPeriod(getMovedPeriod(-1));
    }

    public MonthPeriod increase() {
        List<YearMonth> result = new ArrayList<>(yearMonths.size());
        result.addAll(yearMonths);

        if (currentStep == 1) {
            result = new ArrayList();
            int year = yearMonths.get(0).getYear();
            for (int i = 1; i <= periodLengths[periodLengths.length - 1]; i++)
                result.add(YearMonth.of(year, i));
        }
        return new MonthPeriod(result);
    }

    public MonthPeriod decrease() {
        List<YearMonth> result = new ArrayList<>(yearMonths.size());
        int year = yearMonths.get(0).getYear();
        if (currentStep == LENGTHS_OF_PERIOD[1]) {
            result.add(YearMonth.of(year, 1));
        } else result.addAll(yearMonths);
        return new MonthPeriod(result);
    }

    public List<YearMonth> getYearMonths() {
        return yearMonths;
    }

    public void setYearMonths(List<YearMonth> yearMonths) {
        this.yearMonths = yearMonths;
    }

    private List<YearMonth> getMovedPeriod(int step) {
        List<YearMonth> result = new ArrayList<>(yearMonths.size());
        result.addAll(yearMonths);
        for (int i = 0; i < result.size(); i++) {
            result.set(i, result.get(i).plusMonths(step * currentStep));
        }
        return result;
    }


}
