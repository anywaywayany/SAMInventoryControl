package com.samic.samic.views.administration;

import com.samic.samic.views.MainLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;


@PageTitle("Administration")
@Route(value = "administration", layout = MainLayout.class)
@RolesAllowed("STORAGEADMIN")
public class AdministrationView extends TabSheet {

  private final UserManagement userManagement;
  private final AppSettings appSettings;

  public AdministrationView(UserManagement userManagement,
      AppSettings appSettings) {

    this.userManagement = userManagement;
    this.appSettings = appSettings;

    initUI();
  }

  public void initUI() {
    add("App Einstellungen", appSettings);
    add("Benutzerverwaltung", userManagement);
  }
}
