package com.samic.samic.views.lagerobjekt_erfassen;

import com.samic.samic.components.UIFactory;
import com.samic.samic.components.form.CPEForm;
import com.samic.samic.components.form.SFPForm;
import com.samic.samic.components.form.SupplyForm;
import com.samic.samic.data.entity.*;
import com.samic.samic.services.*;
import com.samic.samic.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@PageTitle("Lagerobjekt erfassen")
@Route(value = "lagerobjektErfassen", layout = MainLayout.class)
@PermitAll
public class LagerobjektErfassenView extends VerticalLayout implements BeforeLeaveObserver {

private Storage storage;
private StorageObject storageObject;
private SFP sfp;
private CPE cpe;
private Supply supply;
private Producer producer;

// List<Storage> storageService =
// List.of(Storage.builder().name("Hauptlager").build(),Storage.builder().name("1Lager").build());

private VerticalLayout storageContainer;
HorizontalLayout formChildContainer = UIFactory.childContainer(JustifyContentMode.START);
ComboBox<Storage> storageComboBox;
// TODO change Repositories to Services when the latter one has been finished
private final ServiceSupply supplyService;
private final ServiceSFP sfpService;
private final ServiceCPE cpeService;
private final ServiceStorageObject storageObjectService;
private final ServiceProducer producerService;
private final ServiceStorage storageService;
private final CPEForm cpeForm;
private final SFPForm sfpForm;
private final SupplyForm supplyForm;

public LagerobjektErfassenView(
	ServiceSupply supplyService,
	ServiceSFP sfpService,
	ServiceCPE cpeService,
	ServiceStorageObject storageObjectService,
	ServiceProducer producerService,
	ServiceStorage storageService,
	CPEForm cpeForm,
	SFPForm sfpForm,
	SupplyForm supplyForm) {
	this.supplyService = supplyService;
	this.sfpService = sfpService;
	this.cpeService = cpeService;
	this.storageObjectService = storageObjectService;
	this.producerService = producerService;
	this.storageService = storageService;
	this.cpeForm = cpeForm;
	this.sfpForm = sfpForm;
	this.supplyForm = supplyForm;
	// ----------------------------
	this.storageObject = storageObjectService.saveStorageObject(StorageObject.builder().name("Temporary Name").build());

	initUI();
}

private void initUI() {
	storageComboBox = new ComboBox<>("Lager auswählen");

	List<Storage> storages = storageService.findAll().toList();
	storageComboBox.setItems(storages);
	storageComboBox.setItemLabelGenerator(Storage::getName);
	storageComboBox.setValue(storages.get(0));

	ComboBox<Type> typeComboBox = new ComboBox<>("Typ auswählen");
	typeComboBox.setItems(Type.class.getEnumConstants());
	typeComboBox.setItemLabelGenerator(Type::getLongVersion);
	typeComboBox.addValueChangeListener(event -> changeForm(event.getValue(), storageComboBox.getValue()));
	this.storageContainer =
		UIFactory.rootComponentContainer(
			"", UIFactory.childContainer(JustifyContentMode.START, storageComboBox));
	add(storageContainer);

	VerticalLayout formRootContainer =
		UIFactory.rootComponentContainer(
			"", UIFactory.childContainer(JustifyContentMode.START, typeComboBox));

	formRootContainer.add(
		formChildContainer,
		UIFactory.childContainer(
			JustifyContentMode.END,
			UIFactory.btnPrimary(
				"Speichern",
				buttonClickEvent -> onSave(typeComboBox.getValue(), storageComboBox.getValue())),
			UIFactory.btnPrimaryError("Abbrechen", buttonClickEvent -> onCancel())));

	add(formRootContainer);
}

private void changeForm(Type value, Storage storage) {
	if (value.equals(Type.ROUTER) || value.equals(Type.SWITCH) || value.equals(Type.IP_PHONE)) {
		this.cpeForm.setCPEBeans(
				Producer.builder().build(),
				CPE.builder().build(), this.storageObject, value, storage);
	formChildContainer.remove(sfpForm);
	formChildContainer.remove(supplyForm);
	formChildContainer.add(cpeForm);
	} else if (value.equals(Type.SFP)) {
		this.sfpForm.setSFPBeans(
				Producer.builder().build(),
				SFP.builder().build(), this.storageObject, value, storage);
	formChildContainer.remove(supplyForm);
	formChildContainer.remove(cpeForm);
	formChildContainer.add(sfpForm);
	} else if (value.equals(Type.SUPPLY)) {
		this.supplyForm.setSupplyBeans(
				Producer.builder().build(),
				Supply.builder().build(), this.storageObject, value, storage);
	formChildContainer.remove(sfpForm);
	formChildContainer.remove(cpeForm);
	formChildContainer.add(supplyForm);
	}
}

private void onCancel() {
	storageObjectService.deleteStorageObjectById(storageObject.getId());
UI.getCurrent().getPage().reload();
}

private void onSave(Type selectedType, Storage value) {
            StorageObject saved;
            StorageObject persisted;
        if (selectedType.equals(Type.ROUTER) || selectedType.equals(Type.SWITCH) || selectedType.equals(Type.IP_PHONE)) {
			if (cpeForm.isValid()) {
				saved = cpeForm.saveStorageObject();
				persisted = storageObjectService.saveStorageObject(saved);
				if (persisted != null) {
					UIFactory.NotificationSuccess("Lagerobjekt erfolgreich gespeichert").open();
				}
			} else {
				UIFactory.NotificationError("Speichern nicht möglich. Eingaben kontrollieren").open();
				return;
			}
        } else if (selectedType.equals(Type.SFP)) {
			if (sfpForm.isValid()) {
				saved = sfpForm.saveStorageObject();
				persisted = storageObjectService.saveStorageObject(saved);
				if (persisted != null) {
					UIFactory.NotificationSuccess("Lagerobjekt erfolgreich gespeichert").open();
				}
			} else {
				UIFactory.NotificationError("Speichern nicht möglich . Eingaben kontrollieren").open();
				return;
			}
        } else if (selectedType.equals(Type.SUPPLY)) {
			if (supplyForm.isValid()) {
				saved = supplyForm.saveStorageObject();
				persisted = storageObjectService.saveStorageObject(saved);
				if (persisted != null) {
					UIFactory.NotificationSuccess("Lagerobjekt erfolgreich gespeichert").open();
				}
			} else {
				UIFactory.NotificationError("Speichern nicht möglich. Eingaben kontrollieren").open();
				return;
			}
        }
        this.storageObject = storageObjectService.saveStorageObject(StorageObject.builder().name("Temporary Name").build());
        changeForm(selectedType, value);
    }

	@Override
	public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {
		storageObjectService.deleteStorageObjectById(storageObject.getId());
	}
}
