package com.interlink;

import java.util.Scanner;

/**
 * Created by employee on 7/18/16.
 */
public class CommandImpl implements Command {
    @Override
    public String getCommand() {
        String command = "";
        Scanner scanIn = new Scanner(System.in);
        command = scanIn.nextLine();
        scanIn.close();
        return command;
    }
}
