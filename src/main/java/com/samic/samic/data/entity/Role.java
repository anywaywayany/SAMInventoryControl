package com.samic.samic.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Role{

    MANAGMENT("Management", "MGT"),
    STORAGEADMIN("Storageadmin", "STAD"),
    FIELDSERVICETECHNICIAN("Fieldservicetechnician", "FST"),
    ORDERFULLFILLMENT("Orderfullfillment", "ORDF"),
    ROJECTMANAGER("Projectmanager", "PM"),
    SUPPORT("Support", "SUP");

    private String longVersion;
    private String shortVersion;

/*

    Role(String longVersion, String shortVersion){
        this.longVersion  = longVersion;
        this.shortVersion = shortVersion;
    }

    public String getLongVersion(){
        return this.longVersion;
    }

    public String getShortVersion(){
        return this.shortVersion;
    }*/
}
