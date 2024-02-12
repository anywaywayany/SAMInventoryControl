package com.samic.samic.exceptions;

public class ObjectTypeException extends RuntimeException  {
    public ObjectTypeException(String message){
        super(message);
    }

    public ObjectTypeException(String message, Throwable cause){
        super(message, cause);
    }
}
