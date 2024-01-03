package com.samic.samic.components.form;

import com.samic.samic.data.entity.Type;
import com.samic.samic.views.administration.NotificationLimit;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import jakarta.annotation.PostConstruct;
import javax.swing.text.StyledEditorKit.BoldAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class NotificationLimitForm extends FormLayout {
  private final TextField type = new TextField("Gerätetyp");
  private final IntegerField min = new IntegerField("Minimale Anzahl");
  private final IntegerField max = new IntegerField("Maximale Anzahl");

  private final Binder<NotificationLimit> notificationLimitBinder = new Binder<> (NotificationLimit.class);

  @PostConstruct
  private void initUI() {
    add(type, min, max);

    notificationLimitBinder.forField(type).withConverter(Type::valueOf, String::valueOf)
        .bind(NotificationLimit::getType, NotificationLimit::setType);
    notificationLimitBinder.bind(min, NotificationLimit::getMinAmount, NotificationLimit::setMinAmount);
    notificationLimitBinder.bind(max, NotificationLimit::getMaxAmount, NotificationLimit::setMaxAmount);

  }

  public void setBean(NotificationLimit notificationLimit) {
    notificationLimitBinder.setBean(notificationLimit);
  }

  public NotificationLimit saveBean() {
    return notificationLimitBinder.getBean();
  }

  public Boolean isValid() {
    notificationLimitBinder.validate();
    return notificationLimitBinder.isValid();
  }
}
