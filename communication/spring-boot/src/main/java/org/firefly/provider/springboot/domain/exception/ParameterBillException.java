package org.firefly.provider.springboot.domain.exception;

public class ParameterBillException extends BillException {
    public ParameterBillException() {
        super();
    }

    public ParameterBillException(String message) {
        super(message);
    }

    public ParameterBillException(Throwable cause) {
        super(cause);
    }

    public ParameterBillException(String message, Throwable cause) {
        super(message, cause);
    }
}
