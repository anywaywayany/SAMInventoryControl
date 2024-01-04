package com.samic.samic.views.administration;

import com.samic.samic.components.UIFactory;
import com.samic.samic.components.form.NotificationLimitForm;
import com.samic.samic.components.form.StorageForm;
import com.samic.samic.data.entity.Storage;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.lineawesome.LineAwesomeIcon;

@Component
@Scope("prototype")
public class AppSettings extends VerticalLayout {
  private IntegerField reservationMaxDuration = new IntegerField("Maximale erlaubte Reservierungsdauer:");
  private final Grid<NotificationLimit> notificationLimitsGrid = new Grid<>(NotificationLimit.class, false);
  private final Grid<Storage> storageGrid = new Grid<>(Storage.class, false);
  private final DataProviderAdmin dataProvider;
  private final NotificationLimitForm notificationLimitForm;
  private final StorageForm storageForm;

  public AppSettings(DataProviderAdmin dataProvider, NotificationLimitForm notificationLimitForm,
      StorageForm storageForm) {
    this.dataProvider = dataProvider;
    this.notificationLimitForm = notificationLimitForm;
    this.storageForm = storageForm;

    reservationMaxDuration.setRequired(true);
    reservationMaxDuration.setValue(dataProvider.getReservationDuration());
    notificationLimitsGrid.setItems(dataProvider.getNotificationLimits());
    notificationLimitsGrid.addColumn(NotificationLimit::getType);
    notificationLimitsGrid.addColumn(item -> "Min " + item.getMinAmount().toString());
    notificationLimitsGrid.addColumn(item -> "Max " + item.getMaxAmount().toString());
    notificationLimitsGrid.getStyle().setBorder("0px");
    notificationLimitsGrid.setMaxHeight("200px");
    //notificationLimitsGrid.setWidthFull();
    notificationLimitsGrid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS);
    notificationLimitsGrid.addComponentColumn(item -> {
      return new Span(
          UIFactory.btnIconWithTooltip(LineAwesomeIcon.EDIT.create(), "Bearbeiten", e -> onEdit(item)),
          UIFactory.btnIconWithTooltip(LineAwesomeIcon.TRASH_SOLID.create(), "Löschen",
              e -> onRemove(item)));
    }).setFrozenToEnd(true);

    storageGrid.setItems(dataProvider.getStorages(10));

    storageGrid.addColumn(Storage::getName);
    storageGrid.addColumn(storage -> storage.getAddress().getStreet());
    storageGrid.addColumn(storage -> storage.getAddress().getHouseNo());
    storageGrid.addColumn(storage -> storage.getAddress().getDoorNo());
    storageGrid.addColumn(storage -> storage.getAddress().getZipCode());
    storageGrid.addColumn(storage -> storage.getAddress().getCity());
    storageGrid.getStyle().setBorder("0px");
    storageGrid.setMaxHeight("300px");
    //storageGrid.setWidthFull();
    storageGrid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS);
    storageGrid.addComponentColumn(item -> {
      return new Span(
          UIFactory.btnIconWithTooltip(LineAwesomeIcon.EDIT.create(), "Bearbeiten", e -> onEdit(item)),
          UIFactory.btnIconWithTooltip(LineAwesomeIcon.TRASH_SOLID.create(), "Löschen",
              e -> onRemove(item)));
    }).setFrozenToEnd(true);



  }


  @PostConstruct
  private void initUI() {
    add(
        UIFactory.childContainer(JustifyContentMode.START,
        UIFactory.rootComponentContainer("Reservierungsdauer",
                reservationMaxDuration,
            UIFactory.btnPrimary("Speichern", e -> onSave(reservationMaxDuration.getValue()))
        ),
        UIFactory.rootComponentContainer("Benachrichtigungslimits",
            UIFactory.childContainer(JustifyContentMode.START,
                notificationLimitsGrid,
                storageGrid
            )),
        UIFactory.rootComponentContainer("Lager bearbeiten",
            UIFactory.childContainer(JustifyContentMode.START,
                storageGrid)))
        );
  }

  private void onRemove(NotificationLimit item) {
    dataProvider.removeNotificationLimit(item);
    notificationLimitsGrid.getDataProvider().refreshAll();
  }
  private void onRemove(Storage storage) {
    dataProvider.removeStorage(storage);
    storageGrid.getDataProvider().refreshAll();
  }

  private void onEdit(NotificationLimit item) {
    Dialog dialog = new Dialog();
    notificationLimitForm.setBean(item);
    dialog.add(
        UIFactory.rootComponentContainer("Benachrichtigungslimit Bearbeiten",
            UIFactory.childContainer(
                JustifyContentMode.START,
                notificationLimitForm
            ),
            UIFactory.childContainer(
                JustifyContentMode.START,
                UIFactory.btnPrimary("Speichern", e -> {onSave(); dialog.close();}),
                UIFactory.btnPrimaryError("Abbrechen", e -> {dialog.close();})
            )
        )
    );

    dialog.open();
  }
  private void onEdit(Storage item) {
    Dialog dialog = new Dialog();
    storageForm.setBean(item);
    dialog.add(
        UIFactory.rootComponentContainer("Lager Bearbeiten",
            UIFactory.childContainer(
                JustifyContentMode.START,
                storageForm
            ),
            UIFactory.childContainer(
                JustifyContentMode.START,
                UIFactory.btnPrimary("Speichern", e -> {onSave(item); dialog.close();}),
                UIFactory.btnPrimaryError("Abbrechen", e -> {dialog.close();})
            )
        )
    );

    dialog.open();
  }

  private void onSave() {
    if (notificationLimitForm.isValid()) {
      dataProvider.saveNotificationLimit(notificationLimitForm.saveBean());
      UIFactory.NotificationSuccess("Benachrichtigungslimit gespeichert").open();
    } else {
      UIFactory.NotificationError("Ändrungen können nicht übernommen werden, da Fehlerhaft").open();
    }
    notificationLimitsGrid.getDataProvider().refreshAll();
  }
  private void onSave(Storage storage) {
    if (storageForm.isValid()) {
      dataProvider.saveStorage(storageForm.saveBean());
      UIFactory.NotificationSuccess("Benachrichtigungslimit gespeichert").open();
    } else {
      UIFactory.NotificationError("Ändrungen können nicht übernommen werden, da Fehlerhaft").open();
    }
    storageGrid.getDataProvider().refreshAll();
  }


  private void onSave(Integer value) {
    dataProvider.saveReservationDuration(value);
    reservationMaxDuration.setValue(dataProvider.getReservationDuration());
    UIFactory.NotificationSuccess("Maximale Reservierungsdauer geändert").open();
  }

}
