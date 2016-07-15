package com.interlink.view;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by employee on 7/14/16.
 */
public interface CalendarViewConfig {

    String generateCalendarText(Supplier<LocalDate> localDate, List<LocalDate> dates);
}
