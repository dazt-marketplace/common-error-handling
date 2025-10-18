package com.dazt.common.error.handling.exception;

import lombok.Getter;

@Getter
public class BaseBusinessException extends RuntimeException {

    private final String message;
    private final String code;

    public BaseBusinessException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public BaseBusinessException(String message, Throwable cause, String code) {
        super(message, cause);
        this.message = message;
        this.code = code;
    }

}
