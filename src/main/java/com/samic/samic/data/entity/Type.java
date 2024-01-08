package com.samic.samic.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor // Error wenn erzeugt  -> java: Konstruktor Type() ist bereits in Enumeration
// at.spengergasse.sj23247abcif.domain.Type definiert
public enum Type {
    IP_PHONE("IP Phone", "IPP"),
    ROUTER("Router", "Rt"),
    SWITCH("Switch", "Sw"),
    SFP("SFP", "SF"),
    SUPPLY("Supply", "SP");

    private String longVersion;
    private String shortVersion;
}
