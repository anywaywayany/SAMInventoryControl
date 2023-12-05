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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import java.util.List;

@PageTitle("Lagerobjekt erfassen")
@Route(value = "lagerobjektErfassen", layout = MainLayout.class)
@PermitAll
public class LagerobjektErfassenView extends VerticalLayout {
    private Storage storage;
    private StorageObject storageObject;
    private SFP sfp;
    private CPE cpe;
    private Supply supply;
    private Producer producer;

    List<Storage> storageService = List.of(Storage.builder().name("Hauptlager").build(),Storage.builder().name("1Lager").build());

    private VerticalLayout storageContainer;
    HorizontalLayout formChildContainer =  UIFactory.childContainer(JustifyContentMode.START);

    private final ServiceSupply supplyService;
    private final ServiceSFP sfpService;
    private final ServiceCPE cpeService;
    private final ServiceStorageObject storageObjectService;
    private final ServiceProducer producerService;
    private final CPEForm cpeForm;
    private final SFPForm sfpForm;
    private final SupplyForm supplyForm;

    public LagerobjektErfassenView(ServiceSupply supplyService,
                                   ServiceSFP sfpService,
                                   ServiceCPE cpeService,
                                   ServiceStorageObject storageObjectService,
                                   ServiceProducer producerService,
                                   CPEForm cpeForm, SFPForm sfpForm, SupplyForm supplyForm ) {
        this.supplyService = supplyService;
        this.sfpService = sfpService;
        this.cpeService = cpeService;
        this.storageObjectService = storageObjectService;
        this.producerService = producerService;
        this.cpeForm = cpeForm;
        this.sfpForm = sfpForm;
        this.supplyForm = supplyForm;
    //----------------------------

        initUI();
        cpeForm.setCPEBeans(Producer.builder().build(), CPE.builder().build(),
                storageObjectService.saveStorageObject(StorageObject.builder().build()));

    }
    private void initUI() {
        ComboBox<Storage> storageComboBox = new ComboBox<>("Lager auswählen");
        storageComboBox.setItems(storageService.stream().toList());
        storageComboBox.setValue(storageService.stream().filter(e -> e.getName().equals("Hauptlager")).findFirst().get());
        storageComboBox.setItemLabelGenerator(Storage::getName);

        ComboBox<Type> typeComboBox = new ComboBox<>("Typ auswählen");
        typeComboBox.setItems(Type.ROUTER, Type.IP_PHONE, Type.SWITCH, Type.SFP, Type.SUPPLY);
        typeComboBox.setItemLabelGenerator(Type::getLongVersion);
        typeComboBox.addValueChangeListener(event -> changeForm(event.getValue()));
        this.storageContainer = UIFactory.rootComponentContainer("",
                UIFactory.childContainer(JustifyContentMode.START,
                        storageComboBox));
        add(storageContainer);


        VerticalLayout formRootContainer = UIFactory.rootComponentContainer("",
                UIFactory.childContainer(JustifyContentMode.START, typeComboBox));


        formRootContainer.add(formChildContainer,UIFactory.childContainer(JustifyContentMode.END,
                UIFactory.btnPrimary("Speichern", buttonClickEvent -> onSave(typeComboBox.getValue(),
                        storageComboBox.getValue())),
                UIFactory.btnPrimaryError("Abbrechen", buttonClickEvent -> onCancel())));

        add(formRootContainer);

    }

    private void changeForm(Type value) {
        if (value.equals(Type.ROUTER) || value.equals(Type.SWITCH) || value.equals(Type.IP_PHONE)) {
            formChildContainer.remove(sfpForm);
            formChildContainer.remove(supplyForm);
            formChildContainer.add(cpeForm);
        } else if (value.equals(Type.SFP)) {
            formChildContainer.remove(supplyForm);
            formChildContainer.remove(cpeForm);
            formChildContainer.add(sfpForm);
        } else if (value.equals(Type.SUPPLY)) {
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
        if (selectedType.equals(Type.ROUTER) || selectedType.equals(Type.SWITCH) || selectedType.equals(Type.IP_PHONE)) {
            var storageObject = saveCPE();
            storageObjectService.saveStorageObject(storageObject);
        } else if (selectedType.equals(Type.SFP)) {
            var storageObject = saveSFP();
            storageObjectService.saveStorageObject(storageObject);
        } else if (selectedType.equals(Type.SUPPLY)) {
            var storageObject = saveSupply();
            storageObjectService.saveStorageObject(storageObject);
        }

        var saved = storageObjectService.findStorageObjectById(storageObject.getId());

        System.out.println(saved.toString());
        //UI.getCurrent().getPage().reload();

    }
}
