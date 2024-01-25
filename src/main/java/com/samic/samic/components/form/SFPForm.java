package com.samic.samic.components.form;

import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.Producer;
import com.samic.samic.data.entity.SFP;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.Type;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
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

  private final TextField type = new TextField("Typ");
  private final TextField wavelength = new TextField("Wellenlänge");
  private final IntegerField nicSpeed = new IntegerField("NIC Speed");
  private final Checkbox isProjectEquipment = new Checkbox("Projektequipment?");
  private final TextField connectionNumber = new TextField("Verbindungsnummer");
  private final TextField trackingNumber = new TextField("Sendungsnummer");
  private final Binder<StorageObject> binderStorageObject = new Binder<>(StorageObject.class, true);
  private final Binder<SFP> binderSFP = new Binder<>(SFP.class);
  private final Select<ObjectType> objectTypeSelect = new Select<>("Gerätetyp", null);
  private final Binder<Producer> binderProducer = new Binder<>(Producer.class);
  //private TextField storageObjectID = new TextField("Lager ID");
  private Select<ObjectType> deviceType = new Select<>("Gerätetyp", null);
  //private TextField producerName = new TextField("Hersteller");
  private TextField serialnumber = new TextField("Seriennummer");
  private HorizontalLayout projectEquipmentContainer =
          UIFactory.childContainer(
                  FlexComponent.JustifyContentMode.START, connectionNumber, trackingNumber);


  @PostConstruct
  private void initUI() {
    //storageObjectID.setReadOnly(true);
    type.setReadOnly(true);
    add(
            /*storageObjectID,*/
            deviceType,
            /*producerName,*/
            serialnumber,
            wavelength,
            nicSpeed,
            type,
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
    /*binderStorageObject.forField(storageObjectID).withNullRepresentation("").withConverter(
        new StringToLongConverter("Id is not Long")).bind(StorageObject::getId, null);*/
    binderStorageObject.bind(
            isProjectEquipment, StorageObject::getProjectDevice, StorageObject::setProjectDevice);
    //		binderStorageObject.forField(name).asRequired().bind(StorageObject::getName, StorageObject::setName);
    binderSFP.forField(serialnumber).asRequired().bind(SFP::getSerialnumber, SFP::setSerialnumber);
    binderSFP.forField(wavelength).asRequired().bind(SFP::getWavelength, SFP::setWavelength);
    binderSFP.forField(nicSpeed).asRequired().bind(SFP::getNicSpeed, SFP::setNicSpeed);

  }

  public void setSFPBeans(List<ObjectType> objectTypeList, Producer producer, SFP sfp,
                          StorageObject storageObject, Type type,
                          Storage storage) {
    objectTypeSelect.setItemLabelGenerator(ObjectType::getName);
    objectTypeSelect.setItems(objectTypeList);

    storageObject.setStorage(storage);
    sfp.setType(type);
    sfp.setProducer(producer);
    binderStorageObject.setBean(storageObject);
    this.type.setValue(type.getLongVersion());
    binderSFP.setBean(sfp);
    binderProducer.setBean(producer);
  }

  public void refresh() {
    binderStorageObject.setBean(new StorageObject());
    binderSFP.setBean(new SFP());
    binderProducer.setBean(new Producer());
  }

  public StorageObject saveStorageObject() {
    StorageObject storageObject = binderStorageObject.getBean();
    storageObject.setSfp(binderSFP.getBean());
    storageObject.getSfp().setProducer(binderProducer.getBean());
    return binderStorageObject.getBean();
  }

  public SFP saveSFP() {
    return binderSFP.getBean();
  }

  public Producer saveProducer() {

    return binderProducer.getBean();
  }

  public Boolean isValid() {
    binderStorageObject.validate();
    binderSFP.validate();
    binderProducer.validate();
    return binderStorageObject.isValid() && binderSFP.isValid() && binderProducer.isValid();
  }
}
