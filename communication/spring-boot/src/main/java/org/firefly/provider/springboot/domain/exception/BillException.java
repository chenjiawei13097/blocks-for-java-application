package org.firefly.provider.springboot.domain.exception;

public class BillException extends RuntimeException {
    public BillException() {
        super();
    }

    public BillException(String message) {
        super(message);
    }

    public BillException(Throwable cause) {
        super(cause);
    }

    public BillException(String message, Throwable cause) {
        super(message, cause);
    }
}
