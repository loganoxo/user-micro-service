package com.zhiyou.taoge.exception;

public class CommonException extends Exception {

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 构造一个基本异常.
     */
    public CommonException(String message) {
        super(message);
    }

    /**
     * 构造一个基本异常.
     */
    public CommonException(String errorCode, String message) {
        this(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}