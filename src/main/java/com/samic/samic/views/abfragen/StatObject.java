package com.samic.samic.views.abfragen;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StatObject {
  private int amountProjectHardware;
  private int amountStandardHardware;
  private int amountPrepared;
  private int amountnewHardware;

}
