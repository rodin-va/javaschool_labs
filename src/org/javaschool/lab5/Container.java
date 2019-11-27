package org.javaschool.lab5;

public class Container {
    public static void main(String[] args) throws TerminalException {
        PinValidator pinValidator = new PinValidator();
        ServerTerminal terminalServer = new ServerTerminal();
        Display display = new ConsoleDisplay();

        Terminal terminal = new ClientTerminal(pinValidator, terminalServer, display);
        terminal.withdraw(Float.valueOf(11.1f));

    }
}
