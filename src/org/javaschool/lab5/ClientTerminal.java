package org.javaschool.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.StringTokenizer;

class ClientTerminalException extends TerminalException {
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

public class ClientTerminal implements Terminal{
    public static final int CONNECTION_ERROR_PERCENT = 30;

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


    public void connectToServer() throws ServerConnectionException {
        if(Math.random() * 100 < CONNECTION_ERROR_PERCENT) {
            throw new ServerConnectionException("Lost connection with Server: Service unreachable");
        }
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
            connectToServer();
            return terminalServer.deposit(sum);
        } catch (org.javaschool.lab5.NegativeOrNullSumException e) {
            throw new CriticalClientTerminalException("Ввденная сумма меньше нуля, повторите ввод");
        } catch (org.javaschool.lab5.NullSumException e) {
            throw new CriticalClientTerminalException("Сумма не указана, повторите ввод");
        } catch (ServerConnectionException e) {
            throw new CriticalClientTerminalException("Потеряна связь с сервером, попробуйте повторить операцию позже");
        } catch (ServerTerminalException e) {
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
            connectToServer();
            return terminalServer.withdraw(sum);
        } catch (org.javaschool.lab5.NegativeOrNullSumException e) {
            throw new CriticalClientTerminalException("Ввденная сумма меньше нуля, повторите ввод");
        } catch (org.javaschool.lab5.NullSumException e) {
            throw new CriticalClientTerminalException("Сумма не указана, повторите ввод");
        } catch (org.javaschool.lab5.NotEnoughMoneyException e) {
            throw new CriticalClientTerminalException("На счете недостаточно средств, введите другую сумму");
        } catch (ServerConnectionException e) {
            throw new CriticalClientTerminalException("Потеряна связь с сервером, попробуйте повторить операцию позже");
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

   public void enterPinCode(int pinCode) throws CriticalClientTerminalException, FatalClientTerminalException, ClientTerminalException {
       try {
           connectToServer();
           pinValidator.validatePinCode(pinCode);
       } catch (AccountIsLockedException e) {
           throw new CriticalClientTerminalException("Исчерпано число попыток для ввода PIN-кода. Доступ к счету заблокирован до " + e.getMessage());
       } catch (InvalidPinCodeException e) {
           throw new CriticalClientTerminalException("Введен некорректный PIN-код, повторите ввод");
       } catch (ServerConnectionException e) {
           throw new CriticalClientTerminalException("Потеряна связь с сервером, попробуйте повторить операцию позже");
       } catch (Throwable e) {
           throw new FatalClientTerminalException("Возникла неизвестная ошибка валидатора.\nПожалуйста, сообщите в техподдержку тел. 8-800-555-35-55", e);
       }
   }

   public void loop() {
       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = null;

       while (true) {
           if (display != null) {
               display.showActionList();

               try {
                   st = new StringTokenizer(in.readLine());
               } catch (IOException e) {
                   display.showMessage(MessageType.CRITICAL,"Ошибка ввода: не удалось считать номер команды.\nПовторите ввод.");
                   continue;
               }

               if(st.hasMoreElements()) {
                    int command;

                    try {
                        command = Integer.parseInt(st.nextToken());

                        if (command <= 0 || command > 4) {
                            display.showMessage(MessageType.CRITICAL,"Ошибка ввода: необходимо ввести число от 1 до 4.\nПовторите ввод.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        display.showMessage(MessageType.CRITICAL,"Ошибка ввода: необходимо ввести целое положительное число.\nПовторите ввод.");
                        continue;
                    }

                    switch (command) {
                       case 1:
                           display.showMessage(MessageType.INFO,"Ошибка ввода: необходимо ввести число от 1 до 4.\nПовторите ввод.");
                           break;
                       case 2:
                           break;
                       case 3:
                           break;
                       case 4:
                           break;
                       default:
                            display.showMessage(MessageType.CRITICAL,"Ошибка ввода: необходимо ввести число от 1 до 4.\nПовторите ввод.");
                            continue;
                   }



               } else {
                   display.showMessage(MessageType.CRITICAL,"Ошибка ввода: не удалось определить номер команды.\nПовторите ввод.");
                   continue;
               }

           } else {
               System.out.println("Поломался дисплей");
           }
       }
   }

} //public class ClientTerminal implements Terminal{
