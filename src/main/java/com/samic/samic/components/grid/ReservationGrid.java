package com.samic.samic.components.grid;

import com.samic.samic.data.entity.Reservation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ReservationGrid extends Grid<Reservation> {

  @PostConstruct
  private void initUI() {
    addColumn(Reservation::getReservedDescription).setHeader("Beschreibung");
    addColumn(item -> item.getCustomer().connectionNo()).setHeader("Verbindungsnummer");
    addColumn(new LocalDateTimeRenderer<>(Reservation::getReservedAt));
    addColumn(item -> item.getStorageObject().getObjectTypeName().getName()).setHeader("Reserviertes Objekt");

    getStyle().setBorder("0px");
    setWidthFull();
  }

  public void populate(List<Reservation> reservations) {
    setItems(reservations);
  }
}
