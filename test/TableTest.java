import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.interlink.view.CalendarView.printDaysByDates;

/**
 * Created by employee on 7/6/16.
 */
public class TableTest {
    public List<LocalDate> generateDateList(LocalDate startDate,LocalDate finishDate){
        List<LocalDate>dates=new ArrayList<>();
        for(LocalDate date=startDate;date.isBefore(finishDate.plusDays(1));date=date.plusDays(1))
            dates.add(date);
        return dates;
    }
    @Test
    public void checkTabsInRow(){
        List<LocalDate>dates=generateDateList(LocalDate.of(2016,4,1),LocalDate.of(2016,4,30));
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        // Print some output: goes to your special stream
        printDaysByDates(dates);
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        String s=baos.toString();
        System.out.println(s);




    }

}
