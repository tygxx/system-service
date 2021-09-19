package com.yy.ds.system.exception;

import com.yy.ds.system.common.IErrorCode;

/*
 *@Description: 断言处理类，用于抛出各种API异常
 *@ClassAuthor: tengYong
 *@Date: 2021-09-18 16:40:06
*/
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
