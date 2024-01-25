package com.samic.samic.exceptions;

public class StorageObjectException extends RuntimeException{
      public StorageObjectException(String message){
            super(message);
      }

      public StorageObjectException(String message, Throwable cause){
            super(message, cause);
      }
}
