package com.samic.samic.data.foundation;

import com.samic.samic.exceptions.SamicException;

public class Guard{

    public static <Q> Q ensureNotNull(Q argument){
        return ensureNotNull(argument, "argument");
    }

    public static <Q> Q ensureNotNull(Q argument, String name){
        if(argument == null){
            throw new SamicException("'%s' must not be null!".formatted(name));
        }
        return argument;
    }
}
