package com.samic.samic.data.foundation;

import java.time.LocalDateTime;

public abstract class DateTimeFactory{

    public static LocalDateTime now(){
        return LocalDateTime.now();
    }

    public static LocalDateTime now_minus_10_years(){
        return LocalDateTime.of(2012, 10, 5, 23, 59);
    }

    public static LocalDateTime now_minus_one_year(){
        return LocalDateTime.now().minusYears(1);
    }

    public static LocalDateTime now_minus_one_week(){
        return LocalDateTime.now().minusWeeks(1);
    }
}