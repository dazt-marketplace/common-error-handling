package com.dazt.common.error.handling.dto;

import com.dazt.common.error.handling.error.ErrorType;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorDetails(
        ErrorType exceptionType,
        String serviceName,
        String path,
        Throwable cause
        //String traceId,
) {
}
