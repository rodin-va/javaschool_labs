package org.javaschool.lab5;

public class Container {
    public static void main(String[] args) throws TerminalException {
        PinValidator pinValidator = new PinValidator();
        ServerTerminal terminalServer = new ServerTerminal();
        Display display = new ConsoleDisplay();

        ClientTerminal terminal = new ClientTerminal(pinValidator, terminalServer, display);
        /*display.showActionList();
        terminal.enterPinCode(2486);
        terminal.withdraw(Float.valueOf(1100f));
        display.showActionList();*/

    }
}
