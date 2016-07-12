import com.interlink.view.CalendarView;
import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.interlink.view.CalendarView.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by employee on 7/6/16.
 */
public class TableTest {
    private List<LocalDate> generateDateList(LocalDate startDate, LocalDate finishDate) {
        List<LocalDate> dates = new ArrayList<>();
        for (LocalDate date = startDate; date.isBefore(finishDate.plusDays(1)); date = date.plusDays(1))
            dates.add(date);
        return dates;
    }

    @Test
    public void titleTest() {
        String realTitle = CalendarView.generateTitleByDate(Month.FEBRUARY, 2014);
        assertThat(realTitle, is("February 2014"));
    }

    @Test
    public void signatureTest() {
        String realSignature = CalendarView.generateRowSignatures();
        assertThat(realSignature, is("  Mon Tue Wed Thu Fri" + ANSI_RED + " Sat" + ANSI_RESET + ANSI_RED +
                " Sun" + ANSI_RESET));
    }

    @Test
    public void daysTestOnRightEnters() {
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 5, 1), LocalDate.of(2016, 5, 31));
        String realDays = CalendarView.generateDaysByDates(dates, LocalDate.of(2016, 2, 1));
        assertThat(realDays, allOf(
                containsString("\n   2"),
                containsString("\n   9"),
                containsString("\n  16"),
                containsString("\n  23"),
                containsString("\n  30")
        ));

    }

    @Test
    public void daysTestOnRightWeekendsColor() {
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 31));
        String realDays = CalendarView.generateDaysByDates(dates, LocalDate.of(2016, 7, 28)
        );
        assertThat(realDays, allOf(containsString((ANSI_RED + "   " + 2 + ANSI_RESET)),
                containsString(ANSI_RED + "   " + 3 + ANSI_RESET))
        );
    }

    @Test
    public void daysTestOnRightTodayDay() {
        List<LocalDate> dates = generateDateList(LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 31));
        String realDays = CalendarView.generateDaysByDates(dates, LocalDate.of(2016, 7, 28));
        assertThat(realDays, containsString(ANSI_BLUE + "  " + 28 + ANSI_RESET));
    }

}
