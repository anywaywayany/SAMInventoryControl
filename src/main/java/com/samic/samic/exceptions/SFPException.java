package com.samic.samic.exceptions;

public class SFPException extends RuntimeException{
      public SFPException(String message){
            super(message);
      }

      public SFPException(String message, Throwable cause){
            super(message, cause);
      }
}
