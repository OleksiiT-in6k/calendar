package com.interlink.view;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Created by employee on 7/14/16.
 */
public interface CalendarViewConfig {
    void setFirstDayOfWeek(DayOfWeek firstDayOfWeek);

    void setWeekends(List<DayOfWeek> weekends);
}
