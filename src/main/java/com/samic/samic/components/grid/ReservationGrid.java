package com.samic.samic.components.grid;

import com.samic.samic.data.entity.Reservation;
import com.samic.samic.services.ServiceStorageObject;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class ReservationGrid extends Grid<Reservation> {

  private final ServiceStorageObject storageObjectService;

  public ReservationGrid(ServiceStorageObject storageObjectService) {
    this.storageObjectService = storageObjectService;
  }

  @PostConstruct
  private void initUI() {
    addColumn(
        item -> item.getId() == null ? ""
            : storageObjectService.findStorageObjectByReservationID(
                item.getId()).getId())
        .setHeader("Lager ID");
    addColumn(new LocalDateTimeRenderer<>(Reservation::getReservedAt)).setHeader("Reserviert bis");
    addColumn(item -> item.getStorageObject().getObjectTypeName().getName()).setHeader(
        "Gerätetyp").setAutoWidth(true);
    addColumn(Reservation::getReservedDescription).setHeader("Beschreibung");

    getStyle().setBorder("0px");
    setWidthFull();
  }

  public void populate(List<Reservation> reservations) {
    setItems(reservations);
  }
}
