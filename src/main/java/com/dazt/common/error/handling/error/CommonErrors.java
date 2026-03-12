package com.dazt.common.error.handling.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrors implements ErrorDefinition {

    GENERIC_ERROR("GENERIC-ERROR", "Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR, ErrorType.TECHNICAL),
    ENTITY_NOT_FOUND("ENTITY-NOT-FOUND", "%s was not found", HttpStatus.NOT_FOUND, ErrorType.BUSINESS),
    UNAUTHORIZED("UNAUTHORIZED", "Authentication is required", HttpStatus.UNAUTHORIZED, ErrorType.BUSINESS);

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;
    private final ErrorType errorType;

}
