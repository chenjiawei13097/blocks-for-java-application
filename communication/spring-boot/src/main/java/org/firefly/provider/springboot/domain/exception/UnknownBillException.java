package org.firefly.provider.springboot.domain.exception;

public class UnknownBillException extends BillException {
    public UnknownBillException() {
        super();
    }

    public UnknownBillException(String message) {
        super(message);
    }

    public UnknownBillException(Throwable cause) {
        super(cause);
    }

    public UnknownBillException(String message, Throwable cause) {
        super(message, cause);
    }
}
