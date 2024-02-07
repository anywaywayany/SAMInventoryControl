package com.samic.samic.views.dashboard;

import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.foundation.Guard;
import com.samic.samic.exceptions.SamicException;
import com.samic.samic.exceptions.StorageObjectException;
import com.samic.samic.security.AuthenticatedUser;
import com.samic.samic.services.ServiceProducer;
import com.samic.samic.services.ServiceReservation;
import com.samic.samic.services.ServiceStorageObject;
import com.samic.samic.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class DashboardView extends VerticalLayout implements BeforeEnterObserver {

  private final ServiceReservation reservationService;
  private final ServiceStorageObject storageObjectService;
  private final AuthenticatedUser authenticatedUser;
  Grid<StorageObject> reservationGrid = new Grid<>(StorageObject.class, false);


  public DashboardView(ServiceReservation reservationService,
      ServiceStorageObject storageObjectService, AuthenticatedUser authenticatedUser,
      ServiceProducer producerService) {
    this.reservationService = reservationService;
    this.storageObjectService = storageObjectService;
    this.authenticatedUser = authenticatedUser;

    initUI();
  }

  private void initUI() {
    initStats();
    initQuickAccess();
    initReservation();
    initHardware();
  }

  private void initStats() {
    // STATS - Container
    VerticalLayout statsContainer = UIFactory.rootComponentContainer("Statistiken");
    HorizontalLayout statsComponents = UIFactory.childContainer(JustifyContentMode.EVENLY);

    HashMap<String, List<Integer>> map = new HashMap<>();
    map.put("Gerät Typ1", List.of(100, 50));
    map.put("Gerät Typ2", List.of(50, 50));
    map.put("Gerät Typ3", List.of(100, 1));

    map.forEach(
        (key, value) -> {
          Span sp = new Span(key + " " + value.get(0) + "/" + value.get(1));
          if (value.get(0) / value.get(1) >= 25) {
            sp.getElement().getThemeList().add("badge error");
          } else if (value.get(0) / value.get(1) >= 2) {
            sp.getElement().getThemeList().add("badge contrast");
          } else {
            sp.getElement().getThemeList().add("badge success");
          }
          statsComponents.add(sp);
        });
    statsContainer.add(statsComponents);
    add(statsContainer);
  }

  private void initQuickAccess() {
    // CSS porperties
    HashMap<String, String> cssValues = new HashMap<>(Map.of("flex", "1 250px"));
    // QUICK_ACCESS - PUT TOGETHER UI
    add(
        UIFactory.rootComponentContainer(
            "Quick-Access",
            UIFactory.childContainer(
                JustifyContentMode.BETWEEN,
                UIFactory.btnPrimary("Lagerobjekt erfassen", cssValues),
                UIFactory.btnPrimary("Reservierung hinzufügen", cssValues),
                UIFactory.btnPrimary("Lagerobjekt aufnehmen", cssValues),
                UIFactory.btnPrimary("Lager hinzufügen", cssValues),
                UIFactory.btnPrimary("Benutzer hinzufügen", cssValues))));
  }

  private void initHardware() {
    // MY_HARDWARE - PREPARE GRID
    Grid<StorageObject> hardwareGrid = new Grid<>(StorageObject.class, false);
    hardwareGrid.isAllRowsVisible();
    hardwareGrid.setMaxHeight("300px");
    hardwareGrid.getStyle().setBorder("0px");

    hardwareGrid.addColumn(StorageObject::getId).setHeader("Lager ID").setAutoWidth(true);
    hardwareGrid.addColumn(so ->
            Guard.isNotNull.test(so.getObjectTypeName()) ? so.getObjectTypeName().getName() : "")
        .setHeader("Gerätetyp Name").setAutoWidth(true);
    initHardwareData();
    // MY_HARDWARE - PUT UI TOGETHER
    add(UIFactory.rootComponentContainer("Meine Hardware", hardwareGrid));
  }

  private void initHardwareData() {
  }

  private void initReservation() {
    // MY_RESERVATIONS
    reservationGrid.isAllRowsVisible();
    reservationGrid.setMaxHeight("300px");
    reservationGrid.getStyle().setBorder("0px");

    reservationGrid.addColumn(so -> Guard.isNotNull.test(so) ? so.getId() : Long.valueOf(12L))
        .setHeader("Lager ID");
    reservationGrid.addColumn(
            so -> Guard.isNotNull.test(so.getReservation()) ? so.getRemark() : "Test")
        .setHeader("Reservierungsbeschreibung").setAutoWidth(true);
    reservationGrid.addColumn(
            so -> Guard.isNotNull.test(so.getReservation()) ? so.getReservation().getReservedAt()
                : "Test")
        .setHeader("Reserviert bis(mom. res. am)").setAutoWidth(true); //Fehlt im Backend
    reservationGrid.addColumn(
            so -> Guard.isNotNull.test(so.getReservation()) ? so.getObjectTypeName() : "Test")
        .setHeader("Gerätetyp Name").setAutoWidth(true);
    initReservationData();

    // MY RESERVATION - PUT UI TOGETHER
    add(UIFactory.rootComponentContainer("Meine Reservierungen", reservationGrid));
  }


  void initReservationData() {
    try {
      System.out.println("Placeholder");
//      reservationGrid.setItems(reservationService
//              .findAllReservationByGivenUser(authenticatedUser
//                                                     .getUser()
//                                                     .get())
//              .map(r -> storageObjectService.findStorageObjectByReservationID(r.getId())).toList());
    } catch (StorageObjectException e) {
      UIFactory.NotificationError(e.getMessage()).open();
    }
  }

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    //VaadinSession.getCurrent().setErrorHandler(new SamicErrorHandler());
  }
}
