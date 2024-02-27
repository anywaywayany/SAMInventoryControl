package com.samic.samic.views.administration;

import com.samic.samic.components.UIFactory;
import com.samic.samic.components.form.UserForm;
import com.samic.samic.data.entity.Profile;
import com.samic.samic.data.entity.User;
import com.samic.samic.security.UserDetailsServiceImpl;
import com.samic.samic.services.ServiceUser;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.dom.ElementFactory;
import jakarta.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.lineawesome.LineAwesomeIcon;

@Component
@Scope("prototype")
public class UserManagement extends VerticalLayout {

  private static final String DATE_PATTERN = "dd.MM.yyyy";
  final VirtualList<User> virtualList = new VirtualList<>();
  private final ServiceUser userService;
  private final UserForm userForm;
  private final UserForm userFormDialog;
  private final UserDetailsServiceImpl userDetailsService;
  private ComponentRenderer<com.vaadin.flow.component.Component, User> userComponentRenderer;

  public UserManagement(ServiceUser userService, UserForm userForm,
      UserForm userFormDialog, UserDetailsServiceImpl userDetailsService) {
    this.userService = userService;
    this.userForm = userForm;
    this.userFormDialog = userFormDialog;
    this.userDetailsService = userDetailsService;
  }

  @PostConstruct
  private void initUI() {
    add(
        UIFactory.childContainer(JustifyContentMode.START,
            UIFactory.rootComponentContainer("Benutzer anlegen",
                userForm),
            UIFactory.rootComponentContainer("Benutzer",
                virtualList)));

    initUserForm();
    initFormActions();
    initVirtualList();
    initVirtualListData();
  }

  private void initFormActions() {
    userForm.add(
        UIFactory.childContainer(JustifyContentMode.START,
            UIFactory.btnPrimary("Erstellen", e -> onCreate()),
            UIFactory.btnPrimary("Abbrechen", e -> onCancel())));
  }

  private void initUserForm() {
    userForm.setBean(User.builder().activated(false).profile(Profile.builder().build()).build());
  }

  private void initVirtualList() {
    initComponentRenderer();
    virtualList.setRenderer(userComponentRenderer);
  }

  private void initVirtualListData() {
    virtualList.setItems(userService.findAll());
  }

  private void initComponentRenderer() {
    userComponentRenderer =
        new ComponentRenderer<>(
            user -> {
              HorizontalLayout cardLayout = new HorizontalLayout();
              cardLayout.setMargin(true);

              Avatar avatar = new Avatar(user.getProfile().getFirstName()
                  + " " + user.getProfile().getLastName()
                  /*person.getPictureUrl()*/);
              avatar.setHeight("64px");
              avatar.setWidth("64px");

              VerticalLayout infoLayout = new VerticalLayout();
              infoLayout.setSpacing(false);
              infoLayout.setPadding(false);
              infoLayout.getElement().appendChild(
                  ElementFactory.createStrong(user.getProfile().getFirstName()
                      + " " + user.getProfile().getLastName()));
              infoLayout.add(new Div(
                  user.getActivated() ?
                      UIFactory.btnIconWithTooltip(LineAwesomeIcon.BAN_SOLID.create(),
                          "Deaktivieren",
                          e -> onDeactivate(user)) :
                      UIFactory.btnIconWithTooltip(LineAwesomeIcon.CHECK_SOLID.create(),
                          "Aktivieren", e -> onActivate(user)),
                  UIFactory.btnIconWithTooltip(LineAwesomeIcon.TRASH_SOLID.create(), "Löschen",
                      e -> onDelete(user)),
                  UIFactory.btnIconWithTooltip(LineAwesomeIcon.EDIT.create(), "Bearbeiten",
                      e -> onEdit(user))));
              infoLayout.add(new Div(new Text(user.getRole().getLongVersion())));

              VerticalLayout contactLayout = new VerticalLayout();
              contactLayout.setSpacing(false);
              contactLayout.setPadding(false);
              contactLayout.add(new Div(new Text(user.getMail())));
              String status =
                  (user.getActivated() != null && user.getActivated()) ? "Aktiviert"
                      : "Deaktiviert";
              contactLayout
                  .add(new Div(
                          new Text("Registriert seit: " + DateTimeFormatter.ofPattern(DATE_PATTERN)
                              .format(user.getCreatedAt()))),
                      new Div(user.getLastLogin() != null
                          ? new Text("Letzer Login: " + DateTimeFormatter.ofPattern(DATE_PATTERN)
                          .format(user.getLastLogin()))
                          : new Text("Nie")),
                      new Div(new Text("Status: " + status)));
              infoLayout
                  .add(new Details("Benutzereigenschaften", contactLayout));

              cardLayout.add(avatar, infoLayout);
              return cardLayout;
            });
  }

  private void onActivate(User user) {
    user.setActivated(true);
    userService.saveUser(user);
    virtualList.getDataProvider().refreshAll();
    initVirtualListData();
    UIFactory.notificationSuccess("Benutzer aktiviert").open();
  }

  private void onCancel() {
    userForm.clearFields();
  }

  private void onCreate() {
    if (userForm.isValid()) {
      userDetailsService.register(userForm.saveBean());
      userForm.clearFields();
      virtualList.getDataProvider().refreshAll();
      initVirtualListData();
      initComponentRenderer();
      initUserForm();
      UIFactory.notificationSuccess("Benutzer angelegt").open();
    } else {
      UIFactory.notificationError("Benutzer konnte nicht angelegt werden").open();
    }
  }

  private void onEdit(User user) {
    Dialog dialog = new Dialog();
    userFormDialog.setBean(user);
    dialog.add(userFormDialog, UIFactory.childContainer(JustifyContentMode.START,
        UIFactory.btnPrimary("Speichern", e -> {
          onSave(userFormDialog);
          dialog.close();
        }),
        UIFactory.btnPrimary("Abbrechen", e -> {
          onCancel();
          dialog.close();
        })));
    dialog.open();
  }

  private void onSave(UserForm userForm) {
    if (userForm.isValid()) {
      userService.saveUser(userForm.saveBean());
      UIFactory.notificationSuccess("Benutzer gespeichert").open();
      virtualList.getDataProvider().refreshAll();
      initVirtualListData();
    } else {
      UIFactory.notificationError("Benutzer konnte nicht geändert werden").open();
    }
  }

  private void onDelete(User user) {
    userService.deleteByObject(user);
    UIFactory.notificationSuccess("Benutzer entfernt").open();
    virtualList.getDataProvider().refreshAll();
  }

  private void onDeactivate(User user) {
    user.setActivated(false);
    userService.saveUser(user);
    virtualList.getDataProvider().refreshAll();
    initVirtualListData();
    UIFactory.notificationSuccess("Benutzer deaktiviert").open();
  }
}
