package com.dazt.common.error.handling.exception;

import lombok.Getter;

import com.dazt.common.error.handling.error.ErrorDefinition;

@Getter
public class BaseBusinessException extends BaseException {

    public BaseBusinessException(ErrorDefinition error, Throwable cause) {
        super(error, cause);
    }

    public BaseBusinessException(ErrorDefinition error, Object... parameters) {
        super(error, parameters);
    }

    public BaseBusinessException(ErrorDefinition error, Throwable cause, Object... parameters) {
        super(error, cause, parameters);
    }
}
