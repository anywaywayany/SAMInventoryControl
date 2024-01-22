package com.samic.samic.components.form;

import com.samic.samic.data.entity.Producer;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.Supply;
import com.samic.samic.data.entity.Type;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SupplyForm extends FormLayout {

  private final TextField description = new TextField("Beschreibung");
  private final IntegerField amount = new IntegerField("Anzahl");
  private final Binder<StorageObject> binderStorageObject = new Binder<>(StorageObject.class, true);
  private final Binder<Supply> binderSupply = new Binder<>(Supply.class);
  //private TextField storageObjectID = new TextField("Lager ID");
  private Select<Supply> deviceType = new Select<>("Gerätetyp", null);

  @PostConstruct
  private void initUI() {
    //storageObjectID.setReadOnly(true);

    add(/*storageObjectID,*/ deviceType, description, amount);

    initBinder();
  }

  private void initBinder() {
	/*binderStorageObject.forField(storageObjectID).withNullRepresentation("").withConverter(
			new StringToLongConverter("Id is not Long")).bind(StorageObject::getId, null);*/
//	binderStorageObject.bind(name, StorageObject::getName, StorageObject::setName);

    binderSupply.forField(description).asRequired("Beschreibung darf nicht leer sein")
        .bind(Supply::getDescription, Supply::setDescription);
    binderSupply.forField(amount).asRequired("Anzahl darf nicht leer sein")
        .withValidator(new IntegerRangeValidator("Anzahl darf nicht 0 sein", 1, 1000))
        .bind(Supply::getAmount, Supply::setAmount);
  }

  public void setSupplyBeans(Producer producer, Supply supply, StorageObject storageObject,
      Type type, Storage storage) {
    storageObject.setStorage(storage);
    binderStorageObject.setBean(storageObject);
    binderSupply.setBean(supply);
  }

  public void refresh() {
    binderStorageObject.setBean(new StorageObject());
    binderSupply.setBean(new Supply());
  }

  public StorageObject saveStorageObject() {
    return binderStorageObject.getBean();
  }

  public Supply saveSupply() {
    return binderSupply.getBean();
  }

  public Boolean isValid() {
    binderStorageObject.validate();
    binderSupply.validate();
    return binderStorageObject.isValid() && binderSupply.isValid();
  }
}
