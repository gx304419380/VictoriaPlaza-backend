package com.fly.victoria.exception;

/**
 * @author guoxiang
 * @version 1.0.0
 * @since 2021/1/22
 */
public class SaveRideTooMuchException extends RuntimeException {
    public SaveRideTooMuchException(String message) {
        super(message);
    }
}
