package com.interlink.model.period;

/**
 * Created by Алекс on 17.07.2016.
 */
public interface PeriodInterface {
    MonthsPeriod increase();

    MonthsPeriod decrease();

    MonthsPeriod next();

    MonthsPeriod previous();
}
