package com.samic.samic.components.form;

import com.nulabinc.zxcvbn.Zxcvbn;
import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.User;
import com.samic.samic.security.UserDetailsServiceImpl;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Aleksandar Zivkovic
 */

@Component
@Scope("prototype")
public class ChangePasswordForm extends Dialog {

  private final PasswordField oldPassword = new PasswordField("Altes Passwort");
  private final PasswordField newPassword = new PasswordField("Neues Passwort");
  private final PasswordField newPasswordConfirm = new PasswordField("Neues Passwort wiederholen");

  private final Binder<User> userBinder = new Binder<>(User.class);
  private final FormLayout formLayout = new FormLayout();
  private final UserDetailsServiceImpl userDetailsService;
  private final Zxcvbn zxcvbn = new Zxcvbn();
  private final Icon checkIcon = VaadinIcon.CHECK.create();

  private Span passwordStrengthText;

  public ChangePasswordForm(UserDetailsServiceImpl userDetailsService) {
    this.userDetailsService = userDetailsService;
  }


  @PostConstruct
  private void initUI() {
    add(formLayout);
    formLayout.add(oldPassword, newPassword, newPasswordConfirm);
    Div buttonDiv = new Div();
    buttonDiv.add(UIFactory.btnPrimary("Passwort ändern", event -> onSave()));
    buttonDiv.add(UIFactory.btnPrimaryError("Abbrechen", event -> onClose()));
    formLayout.add(buttonDiv);
    setCloseOnOutsideClick(false);
    initBinder();
    initPasswordfield();
  }

  private void onClose() {
    this.close();
    oldPassword.clear();
    newPassword.clear();
    newPasswordConfirm.clear();
  }

  public Dialog setBean(User user) {
    userBinder.setBean(user);
    return this;
  }

  private void onSave() {
    if (isValid()) {
      if (userDetailsService.oldPasswordMatches(oldPassword.getValue())) {
        userDetailsService.changePassword(newPassword.getValue());
        UIFactory.notificationSuccess("Passwort erfolgreich geändert").open();
        userBinder.setBean(null);
        onClose();
      } else {
        UIFactory.notificationError("Fehler: Altes Passwort stimmt nicht überein").open();
      }
    } else {
      UIFactory.notificationError("Fehler: Passwort entspricht nicht den Anforderungen").open();
    }
  }

  private void initBinder() {
    userBinder.forField(newPassword).asRequired("Neues Passwort darf nicht leer sein")
        .bind(User::getHashedPassword, User::setHashedPassword);
    userBinder.forField(newPasswordConfirm)
        .asRequired("Neues Passwort wiederholen darf nicht leer sein")
        .withValidator(value -> value.equals(newPassword.getValue()),
            "Passwörter stimmen nicht überein")
        .bind(User::getHashedPassword, User::setHashedPassword);
  }

  private Boolean isValid() {
    userBinder.validate();
    return userBinder.isValid();
  }

  private void initPasswordfield() {
    newPassword.setRevealButtonVisible(true);
    newPasswordConfirm.setRevealButtonVisible(true);
    checkIcon.setVisible(false);
    newPassword.setClassName("Password");
    checkIcon.getStyle().set("color", "var(--lumo-success-color)");
    newPassword.setSuffixComponent(checkIcon);

    Div passwordStrength = new Div();
    passwordStrengthText = new Span();
    passwordStrength.add(new Text(""),
        passwordStrengthText);
    newPassword.setHelperComponent(passwordStrength);

    newPassword.setValueChangeMode(ValueChangeMode.EAGER);
    newPassword.addValueChangeListener(e -> {
      String password = e.getValue();
      updateHelper(password);
    });
    updateHelper("");

  }

  private void updateHelper(String password) {
    int strength = zxcvbn.measure(password).getScore();

    passwordStrengthText.setText(String.valueOf(strength));

    switch (strength) {
      case 0 -> {
        passwordStrengthText.setText("sehr unsicher");
        passwordStrengthText.getStyle().set("color", "#FF0800");
      }
      case 1 -> {
        passwordStrengthText.setText("unsicher");
        passwordStrengthText.getStyle().set("color", "#FF5601");
      }
      case 2 -> {
        passwordStrengthText.setText("mittelmäßig");
        passwordStrengthText.getStyle().set("color", "#FF9B01");
      }
      case 3 -> {
        passwordStrengthText.setText("stark");
        passwordStrengthText.getStyle().set("color", "#300A233");
        checkIcon.setVisible(true);
      }
      case 4 -> {
        passwordStrengthText.setText("sehr stark");
        passwordStrengthText.getStyle().set("color", "#00A233");
        checkIcon.setVisible(true);
      }
    }
  }
}
