package com.samic.samic.components.form;

import com.samic.samic.data.entity.Producer;
import com.samic.samic.data.entity.SFP;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.Supply;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SupplyForm extends FormLayout {
    private TextField storageObjectID =  new TextField("Lager ID");
    private TextField name =  new TextField("name");
    private final TextField type = new TextField("Typ");

    private final TextField description = new TextField("Beschreibung");
    private final IntegerField amount = new IntegerField("Anzahl");

    private final Binder<StorageObject> binderStorageObject = new Binder<>(StorageObject.class, true);
    private final Binder<Supply> binderSupply = new Binder<>(Supply.class);

    @PostConstruct
    private void initUI() {
        add(storageObjectID, name, description, amount , type);


        initBinder();
    }

    private void initBinder() {
        binderStorageObject.bind(name, StorageObject::getName, StorageObject::setName);

        binderSupply.bind(description, Supply::getDescription, Supply::setDescription);
        binderSupply.bind(amount, Supply::getAmount, Supply::setAmount);

    }

    public void setCPEBeans(Producer producer, Supply supply, StorageObject storageObject) {
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

    public Supply saveSFP() {
        return binderSupply.getBean();
    }
}
