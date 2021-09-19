package com.yy.ds.system.exception;

import com.yy.ds.system.common.IErrorCode;

/*
 *@Description: 自定义API异常
 *@ClassAuthor: tengYong
 *@Date: 2021-09-18 16:37:49
*/
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -5977305807904937435L;

    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
