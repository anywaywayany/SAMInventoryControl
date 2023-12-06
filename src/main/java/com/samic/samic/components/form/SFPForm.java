package com.samic.samic.components.form;

import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.CPE;
import com.samic.samic.data.entity.Producer;
import com.samic.samic.data.entity.SFP;
import com.samic.samic.data.entity.StorageObject;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SFPForm extends FormLayout {
    private TextField storageObjectID =  new TextField("Lager ID");
    private TextField name =  new TextField("name");
    private TextField producerName = new TextField("Hersteller");
    private TextField serialnumber = new TextField("Seriennummer");
    private final TextField type = new TextField("Typ");
    private final TextField wavelength = new TextField("Wellenlänge");
    private final IntegerField nicSpeed = new IntegerField("NIC Speed");
    private final Checkbox isProjectEquipment = new Checkbox("Projektequipment?");
    private final TextField connectionNumber = new TextField("Verbindungsnummer");
    private final TextField trackingNumber = new TextField("Sendungsnummer");

    private final Binder<StorageObject> binderStorageObject = new Binder<>(StorageObject.class, true);
    private final Binder<SFP> binderSFP = new Binder<>(SFP.class);
    private final Binder<Producer> binderProducer = new Binder<>(Producer.class);
    private HorizontalLayout projectEquipmentContainer = UIFactory.childContainer(FlexComponent.JustifyContentMode.START, connectionNumber, trackingNumber);


    @PostConstruct
    private void initUI() {
        add(storageObjectID, name, producerName, serialnumber, wavelength, nicSpeed, type, isProjectEquipment);

        isProjectEquipment.addValueChangeListener(event -> {
            if (event.getValue().equals(true)) {
                add(projectEquipmentContainer);
            } else {
                remove(projectEquipmentContainer);
            }
        });

        initBinder();
    }

    private void initBinder() {
        binderStorageObject.bind(isProjectEquipment, StorageObject::getProjectDevice, StorageObject::setProjectDevice);
        binderStorageObject.bind(name, StorageObject::getName, StorageObject::setName);

        binderSFP.bind(serialnumber, SFP::getSerialnumber, SFP::setSerialnumber);
        binderSFP.bind(wavelength, SFP::getWavelength, SFP::setWavelength);
        binderSFP.bind(nicSpeed, SFP::getNicSpeed, SFP::setNicSpeed);

        binderProducer.bind(producerName, Producer::getName, Producer::setName);

    }

    public void setCPEBeans(Producer producer, SFP sfp, StorageObject storageObject) {
        binderStorageObject.setBean(storageObject);
        binderSFP.setBean(sfp);
        binderProducer.setBean(producer);
    }

    public void refresh() {
        binderStorageObject.setBean(new StorageObject());
        binderSFP.setBean(new SFP());
        binderProducer.setBean(new Producer());

    }


    public StorageObject saveStorageObject() {
        return binderStorageObject.getBean();
    }

    public SFP saveSFP() {
        return binderSFP.getBean();
    }

    public Producer saveProducer() {

        return binderProducer.getBean();
    }

}
