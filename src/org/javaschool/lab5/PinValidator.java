package org.javaschool.lab5;

import java.text.SimpleDateFormat;
import java.util.Date;

final class PinValidator {
    private static final byte MAX_ATTEMPTS= 3;
    private static final short PIN_CODE = 2486;
    private static final long LOCKED_TIMEOUT_MS = 5 * 60 * 1000L;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("DD.MM.YYYY HH:mm:ss.S");

    private boolean isValidated;
    private byte attemptsRemaining;
    private Date lockedTo;

    private void reset() {
        this.attemptsRemaining = PinValidator.MAX_ATTEMPTS;
        this.lockedTo = null;
    }

    public PinValidator() {
        this.isValidated = false;
        reset();
    }

    public void validatePinCode(int pinCode) throws AccountIsLockedException, InvalidPinCodeException {
        if (this.lockedTo == null || this.lockedTo.compareTo(new Date()) <= 0) {

            if(this.lockedTo != null) {
                this.lockedTo = null;
                this.attemptsRemaining = 1;
            }

            if (PinValidator.PIN_CODE == pinCode) {
                reset();
                this.isValidated = true;
            } else {
                this.isValidated = false;
                this.attemptsRemaining--;
                if(this.attemptsRemaining == 0) {
                    this.lockedTo = new Date(new Date().getTime() + PinValidator.LOCKED_TIMEOUT_MS);
                    throw new AccountIsLockedException(dateFormat.format(this.lockedTo));
                }

                throw new InvalidPinCodeException("Validator: INVALID PIN-CODE");
            }
        } else {
            throw new AccountIsLockedException(dateFormat.format(this.lockedTo));
        } //if (this.lockedTo == null || this.lockedTo.compareTo(new Date()) <= 0)
    }

    public boolean getIsValidated() {
        return isValidated;
    }
}
