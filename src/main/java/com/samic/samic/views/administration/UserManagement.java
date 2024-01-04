package com.samic.samic.views.administration;

import com.samic.samic.components.UIFactory;
import com.samic.samic.components.form.UserForm;
import com.samic.samic.data.entity.Profile;
import com.samic.samic.data.entity.User;
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
  private final DataProviderAdmin dataProvider;
  private final UserForm userForm;
  private final UserForm userFormDialog;
  VirtualList<User> virtualList = new VirtualList<>();


  public UserManagement(DataProviderAdmin dataProvider, UserForm userForm, UserForm userFormDialog) {
    this.dataProvider = dataProvider;
    this.userForm = userForm;
    this.userFormDialog = userFormDialog;
    userForm.setBean(User.builder().profile(Profile.builder().build()).build());
  }

  @PostConstruct
  private void initUI() {
    virtualList.setItems(dataProvider.getUsers(10));
    virtualList.setRenderer(userComponentRenderer);
    userForm.add(
        UIFactory.childContainer(JustifyContentMode.START,
            UIFactory.btnPrimary("Erstellen", e -> onCreate()),
            UIFactory.btnPrimary("Abbrechen", e -> onCancel())));
    add(
        UIFactory.childContainer(JustifyContentMode.START,
            UIFactory.rootComponentContainer("Benutzer anlegen",
                userForm),
        UIFactory.rootComponentContainer("Benutzer",
            virtualList)));


  }

  private void onCancel() {
    userForm.clearFields();
  }

  private void onCreate() {
    if (userForm.isValid()) {
      dataProvider.saveUser(userForm.saveBean());
      userForm.clearFields();
      virtualList.getDataProvider().refreshAll();
      UIFactory.NotificationSuccess("Benutzer angelegt").open();
    } else {
      UIFactory.NotificationError("Benutzer konnte nicht angelegt werden").open();
    }
  }

  private ComponentRenderer<com.vaadin.flow.component.Component, User> userComponentRenderer = new ComponentRenderer<>(
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
            UIFactory.btnIconWithTooltip(LineAwesomeIcon.BAN_SOLID.create(), "Deaktivieren", e -> onDeactivate(user)),
            UIFactory.btnIconWithTooltip(LineAwesomeIcon.TRASH_SOLID.create(), "Löschen", e -> onDelete(user)),
            UIFactory.btnIconWithTooltip(LineAwesomeIcon.EDIT.create(), "Bearbeiten", e -> onEdit(user))
        ));
        infoLayout.add(new Div(new Text(user.getRole().getLongVersion())));

        VerticalLayout contactLayout = new VerticalLayout();
        contactLayout.setSpacing(false);
        contactLayout.setPadding(false);
        contactLayout.add(new Div(new Text(user.getMail())));
                String status = (user.getActivated() != null && user.getActivated()) ? "Aktiviert" : "Deaktiviert";
        contactLayout
            .add(new Div(new Text("Registriert seit: " + DateTimeFormatter.ofPattern(DATE_PATTERN).format(user.getCreatedAt()))),
                new Div(new Text("Letzer Login: " + DateTimeFormatter.ofPattern(DATE_PATTERN).format(user.getLastLogin()))),
                new Div(new Text("Status: " + status)));
        infoLayout
            .add(new Details("Benutzereigenschaften", contactLayout));

        cardLayout.add(avatar, infoLayout);
        return cardLayout;
      });

  private void onEdit(User user) {
    Dialog dialog = new Dialog();
    userFormDialog.setBean(user);
    dialog.add(userFormDialog,UIFactory.childContainer(JustifyContentMode.START,
        UIFactory.btnPrimary("Speichern", e -> {onSave(userFormDialog); dialog.close();}),
        UIFactory.btnPrimary("Abbrechen", e -> {onCancel(); dialog.close();})));
    dialog.open();
  }

  private void onSave(UserForm userForm) {
    if (userForm.isValid()) {
      dataProvider.saveUser(userForm.saveBean());
      UIFactory.NotificationSuccess("Benutzer gespeichert").open();
      virtualList.getDataProvider().refreshAll();
    } else {
      UIFactory.NotificationError("Benutzer konnte nicht geändert werden").open();
    }
  }
  private void onDelete(User user) {
    dataProvider.removeUser(user);
    UIFactory.NotificationSuccess("Benutzer entfernt").open();
    virtualList.getDataProvider().refreshAll();
  }

  private void onDeactivate(User user) {
    user.setActivated(false);
    dataProvider.saveUser(user);
    virtualList.getDataProvider().refreshAll();
    UIFactory.NotificationSuccess("Benutzer deaktiviert").open();
  }
}
