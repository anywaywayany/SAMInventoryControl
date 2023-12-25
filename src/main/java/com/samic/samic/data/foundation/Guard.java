package com.samic.samic.data.foundation;

import com.samic.samic.exceptions.SamicException;

public abstract class Guard{

    public static <Q> Q ensureNotNull(Q argument){
        return ensureNotNull(argument, "argument");
    }

    public static <Q> Q ensureNotNull(Q argument, String name){
        if(argument == null){
            throw new SamicException("'%s' must not be null!".formatted(name));
        }
        return argument;
    }

    public static boolean isNullBlankOrEmpty(String argument){
        return argument.isBlank() || argument.isEmpty() || argument == null;
    }
}
