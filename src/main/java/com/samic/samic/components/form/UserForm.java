package com.samic.samic.components.form;

import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.Profile;
import com.samic.samic.data.entity.Role;
import com.samic.samic.data.entity.User;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.login.LoginI18n.Form;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserForm extends VerticalLayout {
  private TextField mail = new TextField("Email");
  private PasswordField password = new PasswordField("Passwort");
  private PasswordField passwordConfirm = new PasswordField("Passwort bestätigen");
  private TextField surename = new TextField("Vorname");
  private TextField lastname = new TextField("Nachname");
  private TextField username = new TextField("Benutzername");
  private ComboBox<Role> role = new ComboBox<>("Rolle");

  private Binder<User> binderUser = new Binder<>(User.class);


  @PostConstruct
  private void initUI() {
    binderUser.forField(mail).asRequired().withValidator(new EmailValidator("Eingabe ist keine E-Mail")).bind(User::getMail, User::setMail);
    binderUser.forField(password).asRequired().bind(User::getHashedPassword, User::setHashedPassword);
    binderUser.forField(surename).asRequired().bind("profile.firstName");
    binderUser.forField(lastname).asRequired().bind("profile.lastName");
    binderUser.forField(username).bind("profile.username");
    binderUser.forField(role).asRequired().bind(User::getRole, User::setRole);

    surename.addValueChangeListener(e -> username.setValue(e.getValue().substring(0, 3) + username.getValue()));
    lastname.addValueChangeListener(e -> username.setValue(username.getValue() + e.getValue().substring(0, 3)));
    role.setItems(Role.values());
    role.setItemLabelGenerator(Role::getLongVersion);
    role.setAllowCustomValue(false);
    role.setWidth("300px");
    role.setRequired(true);
    add(
        UIFactory.childContainer(JustifyContentMode.START,
            role,mail, password, passwordConfirm, surename, lastname, username)
    );
  }

  public void setBean(User user) {
    binderUser.setBean(user);
  }

  public User saveBean() {
    return binderUser.getBean();
  }

  public Boolean isValid(){
    binderUser.validate();
    return binderUser.isValid();
  }

  public void clearFields() {
    binderUser.setBean(User.builder().profile(Profile.builder().build()).build());
    passwordConfirm.setValue("");
    role.setValue(null);
  }



}
