package com.samic.samic.data.entity;


//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public enum Status{

    CUSTOMER("Customer", "C"),
    RESERVED("Reserved", "R"),
    MISSING("Missing", "M"),
    PROJECT("Project", "P"),
    AVAILABLE("Available", "A");

    private String longVersion;
    private String shortVersion;

    Status(String longVersion, String shortVersion){
        this.longVersion  = longVersion;
        this.shortVersion = shortVersion;
    }

    public String getLongVersion(){
        return this.longVersion;
    }

    public String getShortVersion(){
        return this.shortVersion;
    }

}

