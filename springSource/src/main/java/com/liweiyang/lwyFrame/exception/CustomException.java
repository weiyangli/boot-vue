package com.liweiyang.lwyFrame.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private int code;

    private String message;

    public CustomException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
    }

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
