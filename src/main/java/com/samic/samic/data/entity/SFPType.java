package com.samic.samic.data.entity;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SFPType{
    SM("SM", "sm"),
    MM("MM", "mm"),
    WDM ("WDM", "wdm");



    private String longVersion;
    private String shortVersion;

}
