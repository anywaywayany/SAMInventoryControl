package com.samic.samic.components.form;

import com.samic.samic.data.entity.Storage;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StorageForm extends FormLayout {

  private final TextField name = new TextField("Lagerbezeichnung");
  private final TextField street = new TextField("Straße");
  private final IntegerField houseNo = new IntegerField("Hausnummer");
  private final IntegerField doorNo = new IntegerField("Türnummer");
  private final IntegerField zipCode = new IntegerField("Postleitzahl");
  private final TextField city = new TextField("Stadt");

  private final Binder<Storage> storageBinder = new Binder<>(Storage.class);

  @PostConstruct
  private void initUI() {
    add(name, street, houseNo, doorNo, zipCode, city);
    storageBinder.forField(name).asRequired().bind(Storage::getName, Storage::setName);
    storageBinder.forField(street).bind("address.street");
    storageBinder.forField(houseNo).bind("address.houseNo");
    storageBinder.forField(doorNo).bind("address.doorNo");
    storageBinder.forField(zipCode).bind("address.zipCode");
    storageBinder.forField(city).bind("address.city");
  }

  public void setBean(Storage storage) {
    storageBinder.setBean(storage);
  }

  public Storage saveBean() {
    return storageBinder.getBean();
  }

  public Boolean isValid() {
    storageBinder.validate();
    return storageBinder.isValid();
  }
}
