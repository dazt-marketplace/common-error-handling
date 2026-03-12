package com.dazt.common.error.handling.dto;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        String errorCode,
        String errorMessage,
        ZonedDateTime timestamp,
        ErrorDetails errorDetails
) {

    public ErrorResponse {
        if (errorCode == null || errorCode.isBlank()) {
            throw new IllegalArgumentException("errorCode in ErrorResponse cannot be null or empty");
        }
        if (errorMessage == null || errorMessage.isBlank()) {
            throw new IllegalArgumentException("errorMessage in ErrorResponse cannot be null or empty");
        }
        if (timestamp == null) {
            timestamp = ZonedDateTime.now(ZoneId.systemDefault());
        }
    }

    public ErrorResponse(final String errorCode, final String errorMessage) {
        this(errorCode, errorMessage, ZonedDateTime.now(ZoneId.systemDefault()), null);
    }

    public ErrorResponse(final String errorCode, final String errorMessage, final ErrorDetails errorDetails) {
        this(errorCode, errorMessage, ZonedDateTime.now(ZoneId.systemDefault()), errorDetails);
    }

}
