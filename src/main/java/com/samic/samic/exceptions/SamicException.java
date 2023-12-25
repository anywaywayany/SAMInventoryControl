package com.samic.samic.exceptions;

public class SamicException extends RuntimeException{
    public SamicException(String message){
        super(message);
    }

    public SamicException(String message, Throwable cause){
        super(message, cause);
    }
}
