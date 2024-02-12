package com.samic.samic.exceptions;

public class SupplyException extends RuntimeException{
      public SupplyException(String message){
            super(message);
      }

      public SupplyException(String message, Throwable cause){
            super(message, cause);
      }
}
