package com.samic.samic.views.administration;

import com.samic.samic.views.MainLayout;
import com.samic.samic.views.meine_hardware.DataProvider;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@PageTitle("Administration")
@Route(value = "administration", layout = MainLayout.class)
@PermitAll
public class AdministrationView extends TabSheet {

  private final DataProvider dataProvider;
  private final UserManagement userManagement;
  private final AppSettings appSettings;
  public AdministrationView(DataProvider dataProvider, UserManagement userManagement,
      AppSettings appSettings) {

    this.dataProvider = dataProvider;
    this.userManagement = userManagement;
    this.appSettings = appSettings;

    initUI();
  }
  public void initUI() {
    add("App Einstellungen", appSettings);
    add("Benutzerverwaltung", userManagement);

  }
}
