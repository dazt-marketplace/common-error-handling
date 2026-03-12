package com.dazt.common.error.handling.exception;

import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.dazt.common.error.handling.error.ErrorDefinition;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorDefinition error;
    private final Throwable cause;
    private final ZonedDateTime zonedDateTime;

    public BaseException(ErrorDefinition error, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        this.zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        this.cause = cause;
    }

    public BaseException(ErrorDefinition error, Object... parameters) {
        super(error.formatMessage(parameters));
        this.error = error;
        this.zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        this.cause = null;
    }

    public BaseException(ErrorDefinition error, Throwable cause, Object... parameters) {
        super(error.formatMessage(parameters), cause);
        this.error = error;
        this.zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        this.cause = cause;
    }
}
