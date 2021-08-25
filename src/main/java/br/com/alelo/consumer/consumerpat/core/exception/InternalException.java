package br.com.alelo.consumer.consumerpat.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InternalException extends RuntimeException {
    private String details;

    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, String details) {
        super(message);
        this.details = details;
    }

    public InternalException(String message, Throwable cause, String details) {
        super(message, cause);
        this.details = details;
    }

    public InternalException(Throwable cause, String details) {
        super(cause);
        this.details = details;
    }

    public InternalException(Throwable cause) {
        super(cause);
    }

    public InternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String details) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.details = details;
    }
}
