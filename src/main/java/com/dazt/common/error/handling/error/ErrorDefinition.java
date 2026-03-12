package com.dazt.common.error.handling.error;


import org.springframework.http.HttpStatus;

/**
 * Interface that defines the fields that an error will have when is being thrown inside the app and
 * will be propagated within microservices
 */
public interface ErrorDefinition {

    String getErrorCode();

    String getMessage();

    HttpStatus getHttpStatus();

    ErrorType getErrorType();

    default String formatMessage(Object... parameters) {
        if (parameters.length == 0) {
            return this.getMessage();
        }
        return String.format(this.getMessage(), parameters);
    }

}
