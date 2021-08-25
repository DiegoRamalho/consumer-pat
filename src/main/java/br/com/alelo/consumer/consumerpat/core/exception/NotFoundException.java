package br.com.alelo.consumer.consumerpat.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NotFoundException extends RuntimeException {
    private String details;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String details) {
        super(message);
        this.details = details;
    }

    public NotFoundException(String message, Throwable cause, String details) {
        super(message, cause);
        this.details = details;
    }

    public NotFoundException(Throwable cause, String details) {
        super(cause);
        this.details = details;
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String details) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.details = details;
    }
}
