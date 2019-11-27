package org.javaschool.lab5;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleDisplay implements Display {
    public static final Integer MAX_INDICATOR_LENGTH = 30;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("DD.MM.YYYY HH:mm:ss.S");

    @Override
    public void showMessage(MessageType messageType, String message) {
        System.out.println(message.toString() + ": " + message);
    }

    @Override
    public void showBalance(Float balance) {
        System.out.printf("Your balance is: %.2f%n", balance);
    }

    @Override
    public void showTime(Date date) {
        System.out.print(dateFormat.format(new Date()));
    }

    @Override
    public void showProgress(int percentComplete) {
        int indicatorCount = Math.min(Math.max(percentComplete, 0), 100);

        String indicatorString = "";
        for (int i = 1; i <= MAX_INDICATOR_LENGTH; i++) {
            if(indicatorCount * MAX_INDICATOR_LENGTH / 100 >= i) {
                indicatorString += ".";
            } else {
                indicatorString += " ";
            }
        }
        System.out.println("Progress: " + indicatorString + String.format("% 4d%%", indicatorCount));
    }

    @Override
    public void showActionList() {

    }
}
