package com.samic.samic.components.form;

import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.Customer;
import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.StorageObject;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SFPForm extends FormLayout {

  private final Checkbox isProjectEquipment = new Checkbox("Projektequipment?");
  private final TextField connectionNumber = new TextField("Verbindungsnummer");
  private final TextField trackingNumber = new TextField("Sendungsnummer");
  private final TextField wavelength = new TextField("Wellenlänge");
  private final IntegerField nicSpeed = new IntegerField("NIC Speed");
  private final Binder<StorageObject> binderStorageObject = new Binder<>(StorageObject.class, true);
  private ComboBox<ObjectType> deviceType = new ComboBox<>("Gerätetyp");
  private TextField serialnumber = new TextField("Seriennummer");
  private HorizontalLayout projectEquipmentContainer =
      UIFactory.childContainer(
          FlexComponent.JustifyContentMode.START, connectionNumber, trackingNumber);

  @PostConstruct
  private void initUI() {

    add(
        deviceType,
        serialnumber,
        wavelength,
        nicSpeed,
        isProjectEquipment);

    isProjectEquipment.addValueChangeListener(
        event -> {
          if (event.getValue().equals(true)) {
            add(projectEquipmentContainer);
          } else {
            remove(projectEquipmentContainer);
          }
        });

    initBinder();
  }

  private void initBinder() {
    binderStorageObject.bind(isProjectEquipment, StorageObject::getProjectDevice,
        StorageObject::setProjectDevice);
    binderStorageObject.forField(deviceType).asRequired("Gerätetyp auswählen")
        .bind(so -> deviceType.getValue(),
            (so, value) -> so.setObjectTypeName(deviceType.getValue()));

    binderStorageObject.forField(serialnumber).asRequired("Seriennummer eingebn")
        .withNullRepresentation("")
        .bind(so -> so.getSfp().getSerialnumber(),
            (so, value) -> so.getSfp().setSerialnumber(value));

    binderStorageObject.forField(wavelength).asRequired("Wellenlänge eingeben")
        .bind(so -> so.getSfp().getWavelength(),
            (so, value) -> so.getSfp().setWavelength(value));
    binderStorageObject.forField(nicSpeed).asRequired("Schnittstellengeschwindigkeit eingeben")
        .bind(so -> so.getSfp().getNicSpeed(),
            (so, value) -> so.getSfp().setNicSpeed(value));

    //TODO bind connectionNumber and trackingNumber
    binderStorageObject.forField(connectionNumber)
        .withNullRepresentation("")
        /*.withValidator(value -> isProjectEquipment.getValue() && !value.isEmpty(),
            "Verbindungsnummer darf nicht leer sein")
        */.withConverter(Integer::valueOf, String::valueOf)
        .bind(so -> so.getStoredAtCustomer().connectionNo(),
            (so, value) -> so.setStoredAtCustomer(Customer.builder().connectionNo(value).build()));

/*
    binderStorageObject.forField(trackingNumber)
        .bind(StorageObject::getTrackingNumber, StorageObject::setTrackingNumber);
*/
  }

  public void setSFPBeans(List<ObjectType> objectTypes, StorageObject storageObject) {

    deviceType.setItemLabelGenerator(ObjectType::getName);
    deviceType.setItems(objectTypes);
    binderStorageObject.setBean(storageObject);

  }

  public void refresh() {
    binderStorageObject.setBean(new StorageObject());
  }

  public StorageObject saveStorageObject() {
    System.out.println(binderStorageObject.getBean().getSfp().getSerialnumber());
    return binderStorageObject.getBean();
  }

  public Boolean isValid() {
    binderStorageObject.validate();
    return binderStorageObject.isValid();
  }

}
