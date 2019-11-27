package org.javaschool.lab5;

class ServerTerminalException extends TerminalException {
    public ServerTerminalException() {
    }

    public ServerTerminalException(String message) {
        super(message);
    }

    public ServerTerminalException(String message, Throwable cause) {
        super(message, cause);
    }
};

class NotEnoughMoneyException extends ServerTerminalException {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
};
class NullSumException extends ServerTerminalException {
    public NullSumException(String message) {
        super(message);
    }
};
class NegativeOrNullSumException extends ServerTerminalException {
    public NegativeOrNullSumException(String message) {
        super(message);
    }
};

final class ServerTerminal implements Terminal{
    private Float accountBalance;

    public ServerTerminal() {
        this.accountBalance = 127834.94F;
    }

    public Float getAccountBalance() throws TerminalException {
        return this.accountBalance;
    }

    @Override
    public Float deposit(Float sum) throws ServerTerminalException {
        /**{ validate block */
        if (sum == null) {
            throw new NullSumException("Server: NullSumException");
        }
        if (sum.compareTo(Float.valueOf(0L)) < 0) {
            throw new NegativeOrNullSumException("Server: NegativeOrNullSumException");
        }
        /**} validate block */

        try {
            this.accountBalance += sum;
            return this.accountBalance;
        } catch (Throwable e) {
            throw new ServerTerminalException("Server: Unknown Exception", e);
        }
    }

    @Override
    public Float withdraw(Float sum) throws ServerTerminalException {
        /**{ validate block */
        if (sum == null) {
            throw new NullSumException("Server: NullSumException");
        }
        if (sum.compareTo(Float.valueOf(0L)) < 0) {
            throw new NegativeOrNullSumException("Server: NegativeOrNullSumException");
        }

        if (this.accountBalance.compareTo(Float.valueOf(sum)) < 0) {
            throw new NotEnoughMoneyException("Server: NotEnoughMoneyException");
        }
        /**} validate block */

        try {
            this.accountBalance -= sum;
            return this.accountBalance;
        } catch (Throwable e) {
            throw new ServerTerminalException("Server: Unknown Exception", e);
        }
    }
}
