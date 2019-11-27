package org.javaschool.lab5;

import java.util.Date;

public interface Display {
    public void showMessage(MessageType messageType, String message);
    public void showBalance(Float balance);
    public void showTime(Date date);
    public void showProgress(int percentComplete);
    public void showActionList();
}
