package com.samic.samic.components.form;

import com.samic.samic.data.entity.ObjectType;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class NotificationLimitForm extends FormLayout {

  private final TextField type = new TextField("Gerätetyp");
  private final IntegerField min = new IntegerField("Minimale Anzahl");
  private final IntegerField max = new IntegerField("Maximale Anzahl");

  private final Binder<ObjectType> notificationLimitBinder = new Binder<>(ObjectType.class);

  @PostConstruct
  private void initUI() {
    add(type, min, max);

    notificationLimitBinder.bind(type, ObjectType::getName, ObjectType::setName);
    /*notificationLimitBinder.bind(min, NotificationLimit::getMinAmount, NotificationLimit::setMinAmount);
    notificationLimitBinder.bind(max, NotificationLimit::getMaxAmount, NotificationLimit::setMaxAmount);
*/
  }

  public void setBean(ObjectType notificationLimit) {
    notificationLimitBinder.setBean(notificationLimit);
  }

  public ObjectType saveBean() {
    return notificationLimitBinder.getBean();
  }

  public Boolean isValid() {
    notificationLimitBinder.validate();
    return notificationLimitBinder.isValid();
  }
}
