package com.samic.samic.components.grid;

import com.samic.samic.data.entity.StorageObject;
import com.vaadin.flow.component.grid.Grid;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StorageObjectGrid extends Grid<StorageObject> {

  @PostConstruct
  public void initUI() {
    getStyle().setBorder("0px");
    setWidthFull();


    addColumn(item -> item.getObjectTypeName().getName()).setHeader("Lagerobjekttyp");
    addColumn(StorageObject::getRemark).setHeader("Anmerkung");
    addColumn(item ->
        item.getSfp() == null ?
            item.getCpe() == null ? " " : item.getCpe().getProducer().getName()
            : item.getSfp().getProducer().getName())
        .setHeader("Hersteller");
    addColumn(item ->
        item.getSfp() == null ?
            item.getCpe() == null ? " " : item.getCpe().getType()
            : item.getSfp().getType())
        .setHeader("Gerätetyp");
    addColumn(item ->
        item.getSfp() == null ?
            item.getCpe() == null ? " " : item.getCpe().getMacAddress()
            : item.getSfp().getSerialnumber())
        .setHeader("SNR/MAC");
    addColumn(item -> item.getSupply() != null ?item.getSupply().getAmount() : "").setHeader("Anzahl");

  }

  public void populate(List<StorageObject> storageObjects) {
    setItems(storageObjects);
  }

}
