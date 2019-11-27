package org.javaschool.lab5;

class ClientTerminalException extends TerminalException {
    public ClientTerminalException() {
    }

    public ClientTerminalException(String message) {
        super(message);
    }

    public ClientTerminalException(String message, Throwable cause) {
        super(message, cause);
    }
};

class AccountIsLockedException extends ClientTerminalException {
    public AccountIsLockedException(String message) {
        super(message);
    }
};
class NoPinValidatedException extends ClientTerminalException {
    public NoPinValidatedException(String message) {
        super(message);
    }
};
class InvalidPinCodeException extends ClientTerminalException {
    public InvalidPinCodeException(String message) {
        super(message);
    }
};
class ServerConnectionException extends ClientTerminalException {
    public ServerConnectionException(String message) {
        super(message);
    }
};
class InvalidSumException extends ClientTerminalException {
    public InvalidSumException(String message) {
        super(message);
    }
};

class CriticalClientTerminalException extends ClientTerminalException {
    public CriticalClientTerminalException(String message) {
        super(message);
    }
};
class FatalClientTerminalException extends ClientTerminalException {
    public FatalClientTerminalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FatalClientTerminalException(String message) {
        super(message);
    }
};

//if(sum % 100 != 0)

public class ClientTerminal implements Terminal{
    private PinValidator pinValidator;
    private ServerTerminal terminalServer;
    private Display display;

    public void setPinValidator(PinValidator pinValidator) {
        this.pinValidator = pinValidator;
    }

    public void setTerminalServer(ServerTerminal terminalServer) {
        this.terminalServer = terminalServer;
    }

    public ClientTerminal(PinValidator pinValidator, ServerTerminal terminalServer, Display display) {
        this.pinValidator = pinValidator;
        this.terminalServer = terminalServer;
        this.display = display;
    }

    @Override
    public Float deposit(Float sum) throws CriticalClientTerminalException, FatalClientTerminalException, NoPinValidatedException, ClientTerminalException  {
        /**{ validate block */
        if(!pinValidator.getIsValidated()) {
            throw new NoPinValidatedException("Для продолжения работы необходимо ввести PIN-код");
        }
        if(sum % 100 != 0) {
            throw new InvalidSumException("Ввденная сумма не кратна 100, повторите ввод");
        }
        /**} validate block */

        try {
            return terminalServer.deposit(sum);
        } catch (org.javaschool.lab5.NegativeOrNullSumException e) {
            throw new CriticalClientTerminalException("Ввденная сумма меньше нуля, повторите ввод");
        } catch (org.javaschool.lab5.NullSumException e) {
            throw new CriticalClientTerminalException("Сумма не указана, повторите ввод");
        } catch (org.javaschool.lab5.ServerTerminalException e) {
            throw new FatalClientTerminalException("При выполнении операции на сервере, возникла неизвестная ошибка.\nПожалуйста, сообщите в техподдержку тел. 8-800-555-35-55", e);
        } catch (Throwable e) {
            throw new FatalClientTerminalException("Возникла неизвестная ошибка терминала.\nПожалуйста, сообщите в техподдержку тел. 8-800-555-35-55", e);
        }
    }

    @Override
    public Float withdraw(Float sum) throws CriticalClientTerminalException, FatalClientTerminalException, NoPinValidatedException, ClientTerminalException {
        /**{ validate block */
        if(!pinValidator.getIsValidated()) {
            throw new NoPinValidatedException("Для продолжения работы необходимо ввести PIN-код");
        }
        if(sum % 100 != 0) {
            throw new InvalidSumException("Ввденная сумма не кратна 100, повторите ввод");
        }
        /**} validate block */

        try {
            return terminalServer.withdraw(sum);
        } catch (org.javaschool.lab5.NegativeOrNullSumException e) {
            throw new CriticalClientTerminalException("Ввденная сумма меньше нуля, повторите ввод");
        } catch (org.javaschool.lab5.NullSumException e) {
            throw new CriticalClientTerminalException("Сумма не указана, повторите ввод");
        } catch (org.javaschool.lab5.NotEnoughMoneyException e) {
            throw new CriticalClientTerminalException("На счете недостаточно средств, введите другую сумму");
        } catch (org.javaschool.lab5.ServerTerminalException e) {
            throw new FatalClientTerminalException("При выполнении операции на сервере, возникла неизвестная ошибка.\nПожалуйста, сообщите в техподдержку тел. 8-800-555-35-55", e);
        } catch (Throwable e) {
            throw new FatalClientTerminalException("Возникла неизвестная ошибка терминала.\nПожалуйста, сообщите в техподдержку тел. 8-800-555-35-55", e);
        }
    }

    @Override
    public Float getAccountBalance() throws NoPinValidatedException, TerminalException {
        if(!pinValidator.getIsValidated()) {
            throw new NoPinValidatedException("Для продолжения работы необходимо ввести PIN-код");
        } else {
            return terminalServer.getAccountBalance();
        }


    }

    /*public void enterPinCode(short pinCode) throws throws CriticalClientTerminalException, FatalClientTerminalException, NoPinValidatedException, ClientTerminalException {
        try {
            pinValidator.validatePinCode(pinCode);
        } catch (AccountIsLockedException e) {
            throw new CriticalClientTerminalException("Исчерпано число попыток для ввода PIN-кода. Доступ к счету заблокирован до " + e.getMessage());
        } catch (InvalidPinCodeException e) {
            throw new CriticalClientTerminalException("На счете недостаточно средств, введите другую сумму");
        }
    }*/


}
