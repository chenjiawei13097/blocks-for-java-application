package org.firefly.provider.cryptography;

public class CryptographyException extends Exception {
    public CryptographyException() {
        super();
    }

    public CryptographyException(String message) {
        super(message);
    }

    public CryptographyException(Throwable cause) {
        super(cause);
    }

    public CryptographyException(String message, Throwable cause) {
        super(message, cause);
    }
}
