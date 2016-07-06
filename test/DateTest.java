import com.interlink.model.CalendarModel;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.time.Month;
import java.time.Year;

/**
 * Created by employee on 7/6/16.
 */
public class DateTest {
    @Before
    public void prepare()throws  Exception{

    }
    public void checkMonth(int year,Month month,int daysQuantity){
        CalendarModel calendarModel=new CalendarModel(month, Year.of(year));
            for(int i=1;i<=daysQuantity;i++)
            assertThat(calendarModel.getDates().get(i-1).getDayOfMonth(),is(i));
    }
    @Test
    public void checkFebruary2016(){
            checkMonth(2016,Month.FEBRUARY,29);
    }

    @Test
    public void checkFebruary2017(){
        checkMonth(2017,Month.FEBRUARY,28);
    }

    public void checkMarch2016(){
        checkMonth(2016,Month.MARCH,31);
    }

    public void checkMarch(){}

    @Test
    public void checkCurrentMonth(){}

}
