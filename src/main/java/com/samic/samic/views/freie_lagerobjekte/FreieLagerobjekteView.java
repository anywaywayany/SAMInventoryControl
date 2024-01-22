package com.samic.samic.views.freie_lagerobjekte;

import com.samic.samic.components.UIFactory;
import com.samic.samic.components.form.ReservationForm;
import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.exceptions.SamicException;
import com.samic.samic.security.AuthenticatedUser;
import com.samic.samic.services.ServiceObjectType;
import com.samic.samic.services.ServiceStorage;
import com.samic.samic.services.ServiceStorageObject;
import com.samic.samic.services.ServiceUser;
import com.samic.samic.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@PageTitle("Freie Lagerobjekte")
@Route(value = "freieLagerobjekte", layout = MainLayout.class)
@PermitAll
public class FreieLagerobjekteView extends VerticalLayout {

  private final TextField searchField = new TextField();
  private final ComboBox<Storage> filterStorage = new ComboBox<>();
  private final ComboBox<ObjectType> filterObjectType = new ComboBox<>();
  private final Dialog reservationDialog = new Dialog();
  private final Grid<StorageObject> grid = new Grid<>(StorageObject.class, false);

  private final ServiceStorageObject storageObjectService;
  private final ServiceObjectType objectTypeService;
  private final AuthenticatedUser authenticatedUser;
  private final ServiceStorage storageService;
  private final ReservationForm reservationForm;

  private StorageObject storageObjectToSave;


  public FreieLagerobjekteView(ServiceStorageObject storageObjectService,
      ServiceObjectType objectTypeService,
      ServiceStorage storageService, AuthenticatedUser authenticatedUser, ServiceUser userService,
      ReservationForm reservationForm) {
    this.storageObjectService = storageObjectService;
    this.objectTypeService = objectTypeService;
    this.authenticatedUser = authenticatedUser;
    this.storageService = storageService;
    this.reservationForm = reservationForm;

    initUI();
  }

  private void initUI() {
    reservationDialog.add(
        UIFactory.rootComponentContainer("Gerät reservieren", reservationForm,
            UIFactory.childContainer(JustifyContentMode.START,
                UIFactory.btnPrimary("Reservieren", onClick -> reserve()),
                UIFactory.btnPrimaryError("Abbrechen", onClick -> onCancel())))
    );
    searchField.setWidth("20%");
    searchField.setPlaceholder("Suchen...");
    searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
    searchField.setValueChangeMode(ValueChangeMode.EAGER);

    grid.addColumn(StorageObject::getId).setHeader("Lager ID").setAutoWidth(true).setFlexGrow(0);
    grid.addColumn(StorageObject::getObjectTypeName).setHeader("Gerätetyp").setAutoWidth(true)
        .setFlexGrow(1);
    grid.addColumn(StorageObject::getRemark).setHeader("Anmerkung").setAutoWidth(true)
        .setFlexGrow(2);
    grid.addComponentColumn(item -> {
      return new Span(
          new Button(VaadinIcon.BOOKMARK.create(), e -> openReservationForm(item)),
          new Button(VaadinIcon.INSERT.create(), e -> addToUser(item)));
    }).setHeader("Aktionen").setAutoWidth(true).setFrozenToEnd(true);
    grid.setItemDetailsRenderer(createStorageObjectDetailsRenderer());
    grid.getStyle().setBorder("0px");

    StorageObject storageObject = StorageObject.builder()
        .objectTypeName(ObjectType.builder().name("Testname").build())
        .remark("TestRemark")
        .build();

    filterStorage.setItemLabelGenerator(Storage::getName);
    filterStorage.setItems(storageService.findAll().toList());
    filterStorage.setAllowCustomValue(false);
    filterStorage.setPlaceholder("Lager auswählen");

    filterObjectType.setItemLabelGenerator(ObjectType::getName);
    filterObjectType.setItems(objectTypeService.findAll().toList());
    filterObjectType.setAllowCustomValue(false);
    filterObjectType.setPlaceholder("Gerätetyp auswählen");
    filterObjectType.setWidth("250px");

    GridListDataView<StorageObject> storageObjectList;

    try {
      storageObjectList = grid.setItems(storageObjectService.findAll().toList());
    } catch (SamicException e) {
      storageObjectList = grid.setItems(List.of());
      UIFactory.NotificationError(e.getMessage()).open();
    }

    GridListDataView<StorageObject> finalStorageObjectList = storageObjectList;
    searchField.addValueChangeListener(e -> finalStorageObjectList.refreshAll());
    filterStorage.addValueChangeListener(e -> finalStorageObjectList.refreshAll());

    storageObjectList.addFilter(storageobject -> {
      String searchTerm = searchField.getValue().trim();
      boolean matchesName = matchesTerm(storageobject.getObjectTypeName().getName(), searchTerm);
      //boolean matchesStorage;

      if (searchTerm.isEmpty()) {
        return true;
      }

      return matchesName; //|| matchesStorage; // || matchesProfession;
    });

    add(
        UIFactory.rootComponentContainer("",
            UIFactory.childContainer(
                JustifyContentMode.START,
                searchField,
                filterStorage,
                filterObjectType
            )),
        UIFactory.rootComponentContainer("",
            UIFactory.childContainer(
                JustifyContentMode.START,
                grid)),
        reservationDialog);
  }

  private void onCancel() {
    this.reservationDialog.close();
    this.storageObjectToSave = null;
  }

  private void reserve() {
    Reservation reservationToSave = reservationForm.save();

    reservationToSave.setReservedAt(LocalDateTime.now());
    reservationToSave.setLastModified(LocalDateTime.now());
    reservationToSave.setReservedFrom(authenticatedUser.getUser().get());

    this.storageObjectToSave.setReservation(reservationToSave);
    storageObjectService.saveStorageObject(storageObjectToSave);

    this.storageObjectToSave = null;
    reservationDialog.close();

    UIFactory.NotificationSuccess("Reservierung erfolgreich durchgeführt").open();
  }

  private boolean matchesTerm(String value, String searchTerm) {
    return value.toLowerCase().contains(searchTerm.toLowerCase());
  }

  private ComponentRenderer<StorageObjectDetailsForm, StorageObject> createStorageObjectDetailsRenderer() {
    return new ComponentRenderer<>(StorageObjectDetailsForm::new,
        StorageObjectDetailsForm::setStorageObject);
  }


  private void openReservationForm(StorageObject item) {
    this.storageObjectToSave = item;
    reservationForm.setBean(Reservation.builder().build());
    reservationDialog.open();
  }

  private void addToUser(StorageObject item) {
    item.setStoredAtUser(authenticatedUser.getUser().get());
    item.setStorage(null);

    storageObjectService.saveStorageObject(item);
    UIFactory.NotificationSuccess("Lagerobjekt erfolgreich deinem Lager hinzugefügt").open();
  }


  private class StorageObjectDetailsForm extends FormLayout {

    private final TextField remark = new TextField("Anmerkung");

    public StorageObjectDetailsForm() {
      remark.setWidth("200px");
      Stream.of(remark).forEach(field -> {
        field.setReadOnly(true);
        add(field);
      });

    }

    protected void setStorageObject(StorageObject storageObject) {
      remark.setValue(storageObject.getRemark());
    }
  }
}
