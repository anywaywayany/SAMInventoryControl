package com.samic.samic.components.form;

import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.Supply;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SupplyForm extends FormLayout {

  private final TextField description = new TextField("Beschreibung");
  private final IntegerField amount = new IntegerField("Anzahl");
  private final BeanValidationBinder<StorageObject> binderStorageObject = new BeanValidationBinder<>(
      StorageObject.class, true);
  private Select<Supply> deviceType = new Select<>("Gerätetyp", null);

  @PostConstruct
  private void initUI() {
    //storageObjectID.setReadOnly(true);

    add(/*storageObjectID,*/ deviceType, description, amount);

    initBinder();
  }

  private void initBinder() {
    //TODO abklaeren ob Supply einen Devicetype hat
   /* binderStorageObject.forField(deviceType)
                .asRequired("Gerätetyp auswählen")
                .bind(so -> so.getSupply().getType(), (so, value) -> so.getSupply().setType(value));
  */
    binderStorageObject.forField(description)
        .withNullRepresentation("")
        .bind(so -> so.getSupply().getDescription(),
            (so, value) -> so.getSupply().setDescription(value));
    binderStorageObject.forField(amount).asRequired("Anzahl darf nicht leer sein")
        .withValidator(new IntegerRangeValidator("Anzahl darf nicht 0 sein", 1, 1000))
        .bind(so -> so.getSupply().getAmount(), (so, value) -> so.getSupply().setAmount(value));
  }

  public void setSupplyBeans(List<ObjectType> objectTypes, StorageObject storageObject) {
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
