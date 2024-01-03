package com.samic.samic.views.administration;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserManagement extends VerticalLayout {

  @PostConstruct
  private void initUI() {
    add(new Text("test"));
  }

}
