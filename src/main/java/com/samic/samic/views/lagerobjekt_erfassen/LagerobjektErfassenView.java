package com.samic.samic.views.lagerobjekt_erfassen;

import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.*;
import com.samic.samic.data.persistence.RepositoryCPE;
import com.samic.samic.data.persistence.RepositorySFP;
import com.samic.samic.data.persistence.RepositoryStorageObject;
import com.samic.samic.data.persistence.RepositorySupply;
import com.samic.samic.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@PageTitle("Lagerobjekt erfassen")
@Route(value = "lagerobjektErfassen", layout = MainLayout.class)
@PermitAll
public class LagerobjektErfassenView extends VerticalLayout {
    Storage storage;
    StorageObject storageObject;
    SFP sfp;
    CPE cpe;

    Supply supply;
    List<Storage> storageService = List.of(Storage.builder().name("Hauptlager").build(),Storage.builder().name("1Lager").build());
    private TextField storageObjectID;
    private TextField name;
    private TextField producer;
    private TextField macAdress;
    private TextField serialnumber;
    private TextField waveLength;
    private IntegerField nicSpeed;
    private TextField description;
    private IntegerField amount;
    private Checkbox isProjectEquipment;
    private final Binder<StorageObject> binder = new BeanValidationBinder<>(StorageObject.class);
    private FormLayout form = new FormLayout();
    private final TextField type = new TextField("Typ");

    private final Binder<StorageObject> binderStorageObject = new Binder<>(StorageObject.class);
    private final Binder<CPE> binderCPE = new Binder<>(CPE.class);
    private final Binder<SFP> binderSFP = new Binder<>(SFP.class);
    private final Binder<Supply> binderSupply = new Binder<>(Supply.class);

    private final RepositorySupply supplyRepository;
    private final RepositorySFP sfpRepository;
    private final RepositoryCPE cpeRepository;
    private final RepositoryStorageObject storageObjectRepository;

    //TODO change Repositories to Services when the latter one has been finished

    public LagerobjektErfassenView(RepositorySupply supplyRepository,
                                   RepositorySFP sfpRepository,
                                   RepositoryCPE cpeRepository, RepositoryStorageObject storageObjectRepository) {
        this.supplyRepository = supplyRepository;
        this.sfpRepository = sfpRepository;
        this.cpeRepository = cpeRepository;
        this.storageObjectRepository = storageObjectRepository;


        initUI();
    }

    private void initBinder() {
        binderStorageObject.bind(isProjectEquipment, StorageObject::getProjectDevice, StorageObject::setProjectDevice);
        binderStorageObject.bind(name, StorageObject::getName, StorageObject::setName);

        binderCPE.bind(serialnumber, CPE::getSerialnumber, CPE::setSerialnumber);
        binderCPE.bind(macAdress, CPE::getMacAddress, CPE::setMacAddress);

        binderSFP.bind(serialnumber, SFP::getSerialnumber, SFP::setSerialnumber);
        binderSFP.bind(waveLength, SFP::getWavelength, SFP::setWavelength);
        binderSFP.bind(nicSpeed, SFP::getNicSpeed, SFP::setNicSpeed);

        binderSupply.bind(description, Supply::getDescription, Supply::setDescription);
        binderSupply.bind(amount, Supply::getAmount, Supply::setAmount);


    }

    private void initUI() {
        ComboBox<Storage> storageComboBox = new ComboBox<>("Lager auswählen");
        storageComboBox.setItems(storageService.stream().toList());
        storageComboBox.setValue(storageService.stream().filter(e -> e.getName().equals("Hauptlager")).findFirst().get());
        storageComboBox.setItemLabelGenerator(Storage::getName);


        ComboBox<Type> typeComboBox = new ComboBox<>("Typ auswählen");
        typeComboBox.setItems(Type.ROUTER, Type.IP_PHONE, Type.SWITCH, Type.SFP, Type.SUPPLY);
        typeComboBox.setItemLabelGenerator(Type::getLongVersion);
        typeComboBox.addValueChangeListener(event -> form.add(initForm(event.getValue().getLongVersion())));

        //PUT TOGETHER UI

        //Select Storage
        add(UIFactory.rootComponentContainer("",
                UIFactory.childContainer(JustifyContentMode.START,
                        storageComboBox)));
        //Formview
        add(UIFactory.rootComponentContainer("",
                UIFactory.childContainer(JustifyContentMode.START, typeComboBox),
                UIFactory.childContainer(JustifyContentMode.START,this.form),
                UIFactory.childContainer(JustifyContentMode.END,
                        UIFactory.btnPrimary("Speichern", buttonClickEvent -> onSave(typeComboBox.getValue(),
                                storageComboBox.getValue())),
                        UIFactory.btnPrimaryError("Abbrechen", buttonClickEvent -> onCancel()))));

    }

    private void onCancel() {
        storageObjectRepository.delete(storageObject);
        //TODO make only the rootCompnentContainer reload that contains the form
        UI.getCurrent().getPage().reload();
    }

    private void onSave(Type selectedType, Storage value) {

        if (List.of(Type.SWITCH, Type.ROUTER, Type.IP_PHONE).contains(selectedType)) {

            cpe = CPE.builder().build();
            binderCPE.writeBeanIfValid(cpe);
            cpe.setType(selectedType);
            //TODO cpe.setProducer();
            //var saved = cpeRepository.save(cpe);

            binderStorageObject.writeBeanIfValid(storageObject);
            storageObject.setStorage(storage);
            storageObject.setCpe(cpe);
            storageObjectRepository.save(storageObject);

        } else if (selectedType.equals(Type.SFP)) {
            binderSFP.writeBeanIfValid(sfp);
            sfp.setType(selectedType);
            //TODO sfp.setProducer();

            //var saved = sfpRepository.save(sfp);
            storageObject.setSfp(sfp);
            storageObject.setStorage(storage);
            storageObjectRepository.save(storageObject);

        } else{
            supply = Supply.builder().build();

            binderSupply.writeBeanIfValid(supply);
            binderStorageObject.writeBeanIfValid(storageObject);

            storageObject.setSupply(supply);
            var saved2 = storageObjectRepository.save(storageObject);


            /*
            System.out.println(storageObjectRepository.findById(saved2.getId());
            System.out.println(supplyRepository.findById(saved2.getSupply().getId()));
             */
        }

        UI.getCurrent().getPage().reload();


    }

    private FormLayout initForm(@NotNull String selectedType) {
        storageObjectID = new TextField("Lager ID");
        name = new TextField("name");
        producer = new TextField("Hersteller");
        macAdress = new TextField("MAC Adresse");
        serialnumber = new TextField("Seriennummer");
        waveLength= new TextField("Wellenlänge");
        nicSpeed = new IntegerField("NIC Geschwindigkeit");
        description = new TextField("Beschreibung");
        amount = new IntegerField("Anzahl");
        isProjectEquipment = new Checkbox("Projekequipment?");

        storageObjectID.setReadOnly(true);
        this.storageObject = StorageObject.builder().build();
        storageObjectID.setValue(String.valueOf(storageObjectRepository.save(storageObject).getId()));

        form.removeAll();
        form.setWidthFull();

        FormLayout fl = new FormLayout(
                storageObjectID,
                producer,
                serialnumber,
                type,
                isProjectEquipment
        );

        fl.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("600px", 5),
                new FormLayout.ResponsiveStep("900px", 8));

        type.setReadOnly(true);
        type.setValue(selectedType);


        if (List.of(Type.SWITCH.getLongVersion(),
                Type.ROUTER.getLongVersion(),
                Type.IP_PHONE.getLongVersion()).contains(selectedType)) {
            fl.add(
                    this.macAdress
            );
            return fl;
        } else if (selectedType.equals(Type.SFP.getLongVersion())) {
            fl.add(
                    waveLength,
                    nicSpeed,
                    isProjectEquipment
            );
            initBinder();
            return fl;
        } else{
            form.removeAll();
            fl.removeAll();
            fl.add(
                    storageObjectID,
                    name,
                    description,
                    amount,
                    type
            );
            initBinder();
            return fl;
        }
    }


}
