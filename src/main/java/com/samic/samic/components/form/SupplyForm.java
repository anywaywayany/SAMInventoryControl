package com.samic.samic.components.form;

import com.samic.samic.data.entity.StorageObject;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SupplyForm extends FormLayout {

  private final TextField description = new TextField("Beschreibung");
  private final IntegerField amount = new IntegerField("Anzahl");
  private final BeanValidationBinder<StorageObject> binderStorageObject = new BeanValidationBinder<>(
      StorageObject.class, true);

  @PostConstruct
  private void initUI() {
    add(description, amount);

    initBinder();
  }

  private void initBinder() {
    binderStorageObject.forField(description)
        .withNullRepresentation("")
        .bind(so -> so.getSupply().getDescription(),
            (so, value) -> so.getSupply().setDescription(value));
    binderStorageObject.forField(amount).asRequired("Anzahl darf nicht leer sein")
        .withValidator(new IntegerRangeValidator("Anzahl darf nicht 0 sein", 1, 1000))
        .bind(so -> so.getSupply().getAmount(), (so, value) -> so.getSupply().setAmount(value));
  }

  public void setSupplyBeans(StorageObject storageObject) {
    binderStorageObject.setBean(storageObject);
  }

  public void refresh() {
    binderStorageObject.setBean(new StorageObject());
  }

  public StorageObject saveStorageObject() {
    return binderStorageObject.getBean();
  }

  public Boolean isValid() {
    binderStorageObject.validate();
    return binderStorageObject.isValid();
  }
}
