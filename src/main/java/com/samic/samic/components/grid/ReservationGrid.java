package com.samic.samic.components.grid;

import com.samic.samic.data.entity.Reservation;
import com.vaadin.flow.component.grid.Grid;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class ReservationGrid extends Grid<Reservation> {

  @PostConstruct
  private void initUI() {
    addColumn(r -> r.getStorageObject() != null ? r.getStorageObject().getId() : ""
    ).setHeader("LagerID");
    addColumn(r -> r.getStorageObject().getObjectTypeName().getName()).setHeader(
        "Gerätetyp").setAutoWidth(true);
    addColumn(Reservation::getReservedDescription).setHeader("Beschreibung");

    getStyle().setBorder("0px");
    setWidthFull();
  }

}
