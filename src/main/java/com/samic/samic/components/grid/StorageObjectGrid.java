package com.samic.samic.components.grid;

import com.samic.samic.data.entity.StorageObject;
import com.vaadin.flow.component.grid.Grid;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StorageObjectGrid extends Grid<StorageObject> {

  @PostConstruct
  public void initUI() {
    getStyle().setBorder("0px");
    setWidthFull();

    addColumn(item -> item.getId() != null ? item.getId() : " ").setHeader("LagerID");
    addColumn(item -> item.getObjectTypeName() != null ? item.getObjectTypeName()
        .getName() : item.getSupply().getDescription()).setHeader("Gerätetyp");
    addColumn(StorageObject::getRemark).setHeader("Anmerkung");
    addColumn(item -> item.getReservation() == null ? " " : item.getReservation()
        .getReservedDescription()).setHeader("Reservierungsbeschreibung");
    addColumn(item -> item.getSfp() == null ? item.getCpe() == null ? " " : item.getCpe()
        .getMacAddress() : item.getSfp()
        .getSerialnumber()).setHeader("SNR/MAC");

  }
}
