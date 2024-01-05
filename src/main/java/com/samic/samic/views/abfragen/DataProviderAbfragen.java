package com.samic.samic.views.abfragen;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DataProviderAbfragen {

  public DataProviderAbfragen() {

  }

  public StatObject getStatObject() {
    return StatObject.builder()
        .amountPrepared(20)
        .amountProjectHardware(30)
        .amountStandardHardware(100)
        .amountnewHardware(300)
        .build();
  }



}
