package com.interlink;

/**
 * Created by Алекс on 17.07.2016.
 */

import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.mockito.Mockito.*;

public class ControllerTest {
    @Test
    public void test() throws Exception {
        Controller controller = new Controller(YearMonth.of(2016, 4), LocalDate.of(2016, 4, 5));
        Controller spy = spy(controller);
        //when(spy.getCommand()).thenReturn("s");
        Mockito.doReturn("s").when(spy).getCommand();
    }
}
