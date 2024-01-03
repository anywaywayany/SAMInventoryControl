package com.samic.samic.views.administration;

import com.samic.samic.data.entity.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

@Getter
@Setter
@Builder
public class NotificationLimit {
  private Type type;
  private Integer minAmount;
  private Integer maxAmount;
}
