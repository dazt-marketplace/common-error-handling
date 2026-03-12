package com.dazt.common.error.handling.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.dazt.common.error.handling.dto.ErrorDetails;
import com.dazt.common.error.handling.dto.ErrorResponse;
import com.dazt.common.error.handling.error.ErrorDefinition;
import com.dazt.common.error.handling.exception.BaseException;

import static com.dazt.common.error.handling.error.CommonErrors.GENERIC_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    private final boolean isDebugEnabled;
    private final String serviceName;

    public GlobalControllerAdvice(@Value("${debug.enabled:false}") final boolean isDebugEnabled,
                                  @Value("${spring.application.name:unknown}") final String serviceName) {
        this.isDebugEnabled = isDebugEnabled;
        this.serviceName = serviceName;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(final Exception ex, final HttpServletRequest request) {
        log.error("Unexpected exception caught, errorMessage: {}", ex.getMessage(), ex);
        final var path = request.getRequestURI();
        final ErrorResponse errorResponse = getErrorResponse(null, path, ex);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(final BaseException baseException, final HttpServletRequest request) {
        final var path = request.getRequestURI();
        final ErrorDefinition error = baseException.getError();
        final ErrorResponse errorResponse = getErrorResponse(error, path, baseException);
        final HttpStatus errorStatus = error.getHttpStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : error.getHttpStatus();
        return new ResponseEntity<>(errorResponse, errorStatus);
    }

    public ErrorResponse getErrorResponse(final ErrorDefinition errorCaught, final String path, final Throwable cause) {
        final ErrorDefinition error = Optional.ofNullable(errorCaught).orElse(GENERIC_ERROR);
        if (isDebugEnabled) {
            final var errorDetails = new ErrorDetails(error.getErrorType(), serviceName, path, cause);
            return new ErrorResponse(error.getErrorCode(), error.getMessage(), errorDetails);
        } else {
            return new ErrorResponse(error.getErrorCode(), error.getMessage());
        }
    }


}
