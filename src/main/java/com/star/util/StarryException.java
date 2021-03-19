package com.star.util;

/**
 * @Author: zzStar
 * @Date: 03-19-2021 11:59
 */
public class StarryException extends RuntimeException {

    public StarryException() {
    }

    public StarryException(String message) {
        super(message);
    }

    public StarryException(String message, Throwable cause) {
        super(message, cause);
    }
}
