package com.interlink.view;

/**
 * Created by employee on 7/12/16.
 */
public abstract class AbstractCalendarView {
    private String printInColor(String text, String tagOpened,String tagClosed) {
        String outPut = "";
        outPut += tagOpened;
        outPut += String.format("%4s", text);
        outPut += tagClosed;
        return outPut;
    }
}
