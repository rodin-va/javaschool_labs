package org.javaschool.lab5;

class TerminalException extends Exception {
    public TerminalException() {
    }

    public TerminalException(String message) {
        super(message);
    }

    public TerminalException(String message, Throwable cause) {
        super(message, cause);
    }
};

public interface Terminal {
    public Float deposit(Float sum) throws TerminalException;
    public Float withdraw(Float sum) throws TerminalException;
    public Float getAccountBalance() throws TerminalException;
}
