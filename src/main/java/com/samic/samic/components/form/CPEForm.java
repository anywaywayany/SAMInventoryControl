package com.samic.samic.components.form;

import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.*;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CPEForm extends FormLayout {

private TextField storageObjectID = new TextField("Lager ID");
private TextField name = new TextField("name");
private TextField producerName = new TextField("Hersteller");
private TextField macAdress = new TextField("MAC Adresse");
private TextField serialnumber = new TextField("Seriennummer");
private final TextField type = new TextField("Typ");
private final Checkbox isProjectEquipment = new Checkbox("Projektequipment?");
private final TextField connectionNumber = new TextField("Verbindungsnummer");
private final TextField trackingNumber = new TextField("Sendungsnummer");

private final Binder<StorageObject> binderStorageObject = new Binder<>(StorageObject.class, true);
private final Binder<CPE> binderCPE = new Binder<>(CPE.class);
private final Binder<Producer> binderProducer = new Binder<>(Producer.class);
private HorizontalLayout projectEquipmentContainer =
	UIFactory.childContainer(
		FlexComponent.JustifyContentMode.START, connectionNumber, trackingNumber);

@PostConstruct
private void initUI() {
	storageObjectID.setReadOnly(true);
	name.setRequired(true);
	macAdress.setRequired(true);
	type.setReadOnly(true);

	add(storageObjectID, name, producerName, macAdress, serialnumber, type, isProjectEquipment);

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
	binderStorageObject.forField(storageObjectID).withConverter(
			new StringToLongConverter("Id is not Long")).bind(StorageObject::getId, null);
	binderStorageObject.bind(isProjectEquipment, StorageObject::getProjectDevice, StorageObject::setProjectDevice);
//	binderStorageObject.forField(name).asRequired("Lagerobjekt kann nicht ohne namen gespeichert werden").bind(StorageObject::getName, StorageObject::setName);

	binderCPE.forField(serialnumber).asRequired("Seriennummer darf nicht leer sein").bind(CPE::getSerialnumber, CPE::setSerialnumber);
	binderCPE.forField(macAdress).asRequired("Mac Adresse darf nicht leer sein").bind(CPE::getMacAddress, CPE::setMacAddress);

	binderProducer.bind(producerName, Producer::getName, Producer::setName);
}


public void setCPEBeans(Producer producer, CPE cpe, StorageObject storageObject, Type type, Storage storage) {
	storageObject.setStorage(storage);
	cpe.setType(type);
	cpe.setProducer(producer);
	binderStorageObject.setBean(storageObject);
	this.type.setValue(type.getLongVersion());
	binderCPE.setBean(cpe);
	binderProducer.setBean(producer);
}

public void refresh() {
	binderStorageObject.setBean(new StorageObject());
	binderCPE.setBean(new CPE());
	binderProducer.setBean(new Producer());
}

public StorageObject saveStorageObject() {
	StorageObject storageObject = binderStorageObject.getBean();
	storageObject.setCpe(binderCPE.getBean());
	storageObject.getCpe().setProducer(binderProducer.getBean());
	return binderStorageObject.getBean();
}

public CPE saveCPE() {
	return binderCPE.getBean();
}

public Producer saveProducer() {

	return binderProducer.getBean();
}
	public Boolean isValid() {
		binderStorageObject.validate();
		binderCPE.validate();
		binderProducer.validate();
		return binderStorageObject.isValid() && binderCPE.isValid() && binderProducer.isValid();
	}
}
