package com.samic.samic.views.meine_hardware;

import com.samic.samic.components.UIFactory;
import com.samic.samic.components.form.CPEForm;
import com.samic.samic.components.form.ReservationForm;
import com.samic.samic.components.form.SFPForm;
import com.samic.samic.components.form.SupplyForm;
import com.samic.samic.components.grid.ReservationGrid;
import com.samic.samic.components.grid.StorageObjectGrid;
import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.security.AuthenticatedUser;
import com.samic.samic.services.ServiceReservation;
import com.samic.samic.services.ServiceStorage;
import com.samic.samic.services.ServiceStorageObject;
import com.samic.samic.views.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import java.util.List;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Meine Hardware")
@Route(value = "meineHardware", layout = MainLayout.class)
@PermitAll
public class MeineHardwareView extends TabSheet {


  private final ReservationGrid reservationGrid;
  private final ServiceStorageObject storageObjectService;
  private final ServiceStorage storageService;
  private final ReservationForm reservationForm;
  private final StorageObjectGrid storageObjectGrid;
  private final ServiceReservation reservationService;
  private final AuthenticatedUser authenticatedUser;

  private final CPEForm cpeForm;
  private final SFPForm sfpForm;
  private final SupplyForm supplyForm;


  public MeineHardwareView(ServiceStorage storageService, ReservationGrid reservationGrid,
      ServiceStorageObject storageObjectService, ReservationForm reservationForm,
      StorageObjectGrid storageObjectGrid, ServiceReservation reservationService,
      AuthenticatedUser authenticatedUser, CPEForm cpeForm, SFPForm sfpForm,
      SupplyForm supplyForm) {
    this.reservationGrid = reservationGrid;
    this.storageService = storageService;
    this.storageObjectService = storageObjectService;
    this.reservationForm = reservationForm;
    this.storageObjectGrid = storageObjectGrid;
    this.reservationService = reservationService;
    this.authenticatedUser = authenticatedUser;
    this.cpeForm = cpeForm;
    this.sfpForm = sfpForm;
    this.supplyForm = supplyForm;
    initUI();
  }


  private void initUI() {
    add("Meine Hardware", UIFactory.LazyComponent(
        () -> {
          storageObjectGrid.setItems(
              storageObjectService.findStorageObjectByGivenUser(authenticatedUser.getUser().get())
                  .toList());
          storageObjectGrid.addComponentColumn(item -> {
            return new Span(
                UIFactory.btnIconWithTooltip(LineAwesomeIcon.TRUCK_MOVING_SOLID.create(),
                    "Ins Lager verschieben", e -> moveToStorage(item)),
                UIFactory.btnIconWithTooltip(LineAwesomeIcon.EDIT_SOLID.create(), "Bearbeiten",
                    e -> onEdit(item)));
          });
          return this.storageObjectGrid;
        }));

    add(
        "Meine Reservierungen", UIFactory.LazyComponent(
            () -> {
              reservationGrid.populate(
                  reservationService.findAllReservationByGivenUser(authenticatedUser.getUser()
                      .get()).toList());
              reservationGrid.addComponentColumn(item -> {
                return new Span(
                    UIFactory.btnIconWithTooltip(LineAwesomeIcon.TRASH_SOLID.create(), "Löschen",
                        e -> onDelete(item)),
                    UIFactory.btnIconWithTooltip(LineAwesomeIcon.EDIT.create(), "Bearbeiten",
                        e -> onEdit(item)));
              });
              return this.reservationGrid;
            }));
  }

  private void onEdit(StorageObject item) {
    Dialog dialog = new Dialog();
    HorizontalLayout hl = UIFactory.childContainer(JustifyContentMode.START);
    dialog.add(
        UIFactory.rootComponentContainer("Bearbeiten",
            hl,
            UIFactory.childContainer(JustifyContentMode.START,
                UIFactory.btnPrimary("Speichern", e -> {
                  dialog.close();
                  onSave(item);
                }),
                UIFactory.btnPrimaryError("Abbrechen", e -> dialog.close()))));
    if (item.getCpe() != null) {
      //TODO
      cpeForm.setCPEBeans(List.of(item.getObjectTypeName()), item);
      hl.add(cpeForm);
    } else if (item.getSfp() != null) {
      sfpForm.setSFPBeans(List.of(item.getObjectTypeName()), item);
      hl.add(sfpForm);
    } else {
      supplyForm.setSupplyBeans(List.of(item.getObjectTypeName()), item);
      hl.add(supplyForm);
    }
    dialog.open();
  }

  private void onSave(StorageObject storageObject) {
    storageObjectService.saveStorageObject(storageObject);
    UIFactory.NotificationSuccess("Lagerobjekt geändert").open();
    storageObjectGrid.getDataProvider().refreshAll();
  }

  private void moveToStorage(StorageObject storageObject) {
    Dialog dialog = new Dialog();
    ComboBox<Storage> storages = new ComboBox<>("Lager", storageService.findAll().toList());
    storages.setItemLabelGenerator(Storage::getName);
    storages.setAllowCustomValue(false);

    dialog.add(
        UIFactory.rootComponentContainer("Neue Ablage/Lager auswählen ",
            UIFactory.childContainer(JustifyContentMode.START,
                storages),
            UIFactory.childContainer(JustifyContentMode.END,
                UIFactory.btnPrimary("Speichern", e -> {
                  if (storages.getValue() == null) {
                    UIFactory.NotificationError("Lager kann nicht leer sein").open();
                  } else {
                    onSave(storageObject, storages.getValue());
                    dialog.close();
                  }
                }),
                UIFactory.btnPrimaryError("Abbrechen", e -> dialog.close()))));

    dialog.open();
  }

  private void onSave(StorageObject storageObject, Storage storage) {
    storageObject.setStorage(storage);
    storageObjectService.saveStorageObject(storageObject);
    storageObjectGrid.getDataProvider().refreshAll();
  }


  private void onEdit(Reservation item) {
    reservationForm.setBean(item);
    Dialog dialog = new Dialog();
    dialog.add(reservationForm,
        UIFactory.btnPrimary("Speichern", e -> {
          onSave(item);
          dialog.close();
        }),
        UIFactory.btnPrimaryError("Abbrechen", e -> dialog.close()));
    dialog.open();

  }

  private void onSave(Reservation item) {
    reservationService.saveReservationByObject(reservationForm.save());
    reservationGrid.getDataProvider().refreshAll();
  }

  private void onDelete(Reservation item) {
    reservationService.deleteByObject(item);
    reservationGrid.getDataProvider().refreshAll();
    UIFactory.NotificationSuccess("Deleted");
  }

}