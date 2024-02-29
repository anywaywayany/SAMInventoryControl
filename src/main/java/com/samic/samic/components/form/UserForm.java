package com.samic.samic.components.form;

import com.nulabinc.zxcvbn.Zxcvbn;
import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.Profile;
import com.samic.samic.data.entity.Role;
import com.samic.samic.data.entity.User;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserForm extends VerticalLayout {

  private final TextField mail = new TextField("Email");
  private final PasswordField password = new PasswordField("Passwort");
  private final PasswordField passwordConfirm = new PasswordField("Passwort bestätigen");
  private final TextField surname = new TextField("Vorname");
  private final TextField lastname = new TextField("Nachname");
  private final TextField username = new TextField("Benutzername");
  private final ComboBox<Role> role = new ComboBox<>("Rolle");
  private final Binder<User> binderUser = new Binder<>(User.class);
  private final Zxcvbn zxcvbn = new Zxcvbn();
  private Icon checkIcon = VaadinIcon.CHECK.create();
  private Span passwordStrengthText;


  @PostConstruct
  private void initUI() {
    add(
        UIFactory.childContainer(JustifyContentMode.BETWEEN,
            role),
        UIFactory.childContainer(JustifyContentMode.START, mail, password, passwordConfirm),
        UIFactory.childContainer(JustifyContentMode.START, surname, lastname, username)
    );

    initBinder();
    initRolesComboBox();
    initRolesData();
    initPasswordfield();
  }

  private void initPasswordfield() {
    checkIcon.setVisible(false);
    password.setClassName("Password");
    checkIcon.getStyle().set("color", "var(--lumo-success-color)");
    password.setSuffixComponent(checkIcon);

    Div passwordStrength = new Div();
    passwordStrengthText = new Span();
    passwordStrength.add(new Text(""),
        passwordStrengthText);
    password.setHelperComponent(passwordStrength);

    password.setValueChangeMode(ValueChangeMode.EAGER);
    password.addValueChangeListener(e -> {
      String password = e.getValue();
      updateHelper(password);
    });
    updateHelper("");

  }

  private void initBinder() {
    binderUser.forField(mail).asRequired()
        .withValidator(new EmailValidator("Eingabe ist keine E-Mail"))
        .bind(User::getMail, User::setMail);
    binderUser.forField(password).asRequired()
        .bind(User::getHashedPassword, User::setHashedPassword);
    binderUser.forField(passwordConfirm).asRequired()
        .withValidator(p -> p.equals(password.getValue()),
            "Passwörter stimmen nicht überein")
        .bind(User::getHashedPassword, User::setHashedPassword);
    binderUser.forField(surname).asRequired().bind("profile.firstName");
    binderUser.forField(lastname).asRequired().bind("profile.lastName");
    binderUser.forField(username).asRequired().bind("profile.username");
    binderUser.forField(role).asRequired().bind(User::getRole, User::setRole);
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

  private void initRolesComboBox() {
    role.setItemLabelGenerator(Role::getLongVersion);
    role.setAllowCustomValue(false);
    role.setWidth("300px");
    role.setRequired(true);
  }

  private void initRolesData() {
    role.setItems(Role.values());
  }

  public void setBean(User user) {
    binderUser.setBean(user);
  }

  public User saveBean() {
    return binderUser.getBean();
  }

  public Boolean isValid() {
    binderUser.validate();
    return binderUser.isValid()
        /*&& zxcvbn.measure(binderUser.getBean().getHashedPassword()).getScore() > 3*/;
  }

  public void clearFields() {
    binderUser.setBean(User.builder().activated(false).profile(Profile.builder().build()).build());
    passwordConfirm.setValue("");
    role.setValue(null);
  }


}
