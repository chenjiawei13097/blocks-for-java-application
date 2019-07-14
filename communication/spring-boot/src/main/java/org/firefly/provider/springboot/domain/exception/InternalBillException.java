package org.firefly.provider.springboot.domain.exception;

public class InternalBillException extends BillException {
    public InternalBillException() {
        super();
    }

    public InternalBillException(String message) {
        super(message);
    }

    public InternalBillException(Throwable cause) {
        super(cause);
    }

    public InternalBillException(String message, Throwable cause) {
        super(message, cause);
    }
}
