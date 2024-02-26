package com.samic.samic.views.lagerobjekt_aufnehmen;

import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.security.AuthenticatedUser;
import com.samic.samic.services.ServiceStorageObject;
import com.samic.samic.services.ServiceUser;
import com.samic.samic.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;


@PageTitle("Lagerobjekt aufnehmen")
@Route(value = "lagerobjektAufnehmen", layout = MainLayout.class)
@RolesAllowed({"FIELDSERVICETECHNICIAN", "STORAGEADMINISTRATOR"})
public class LagerobjektAufnehmenView extends VerticalLayout {

  private final ServiceStorageObject storageObjectService;
  private final TextField storageObjectID = new TextField("Lager ID");
  private final Binder<StorageObject> binderStorageObject = new Binder<>(StorageObject.class, true);
  private final AuthenticatedUser authenticatedUser;
  private final ServiceUser userService;
  private StorageObject storageObject;


  LagerobjektAufnehmenView(ServiceStorageObject storageObjectService,
      AuthenticatedUser authenticatedUser, ServiceUser userService) {
    this.storageObjectService = storageObjectService;
    this.authenticatedUser = authenticatedUser;
    this.userService = userService;
    initUI();
  }

  private void initBinder() {
    binderStorageObject.forField(storageObjectID).asRequired("Lager ID muss augefüllt werden")
        .withConverter(
            new StringToLongConverter("Id is not Long")).bind(StorageObject::getId, null);
    binderStorageObject.setBean(storageObject);
  }

  private void initUI() {
    add(UIFactory.rootComponentContainer("",
        UIFactory.childContainer(JustifyContentMode.START, storageObjectID),
        UIFactory.childContainer(JustifyContentMode.END,
            UIFactory.btnPrimary("Aufnehmen", event -> onSave()),
            UIFactory.btnPrimaryError("Abbrechen", event -> onCancel()))));
  }

  private void onCancel() {
    UI.getCurrent().getPage().reload();
  }

  private void onSave() {
    try {
      var toEdit = storageObjectService.findStorageObjectById(
          Long.valueOf(storageObjectID.getValue()));
      if (toEdit != null) {
        if (toEdit.getStoredAtUser() == null) {
//          toEdit.setStoredAtUser(authenticatedUser.getUser()
//              .orElse(userService.findUserByID(authenticatedUser.getUser().get().getId())));
          toEdit.setStorage(null);
          storageObjectService.saveStorageObject(toEdit);
          storageObjectID.setValue("");
          UIFactory.notificationSuccess("Lagerobjekt wurde aufgenommen").open();
        } else {
          UIFactory.notificationError("Das Lagerobjekt ist nicht verfügbar!").open();
        }
      }
    } catch (NoSuchElementException e) {
      UIFactory.notificationError(
          "Konnte nicht aufgenommen werden, da das Lagerobjekt nicht existiert").open();
    }
  }
}
