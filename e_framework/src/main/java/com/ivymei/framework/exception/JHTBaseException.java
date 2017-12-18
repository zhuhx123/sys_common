package com.ivymei.framework.exception;

/**
 * Created by kandy on 2016/11/9.
 */
public class JHTBaseException extends RuntimeException{
    public JHTBaseException(String message) {
        super(message);
    }

    public JHTBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
