package com.samic.samic.views.administration;

import com.samic.samic.components.UIFactory;
import com.samic.samic.components.form.UserForm;
import com.samic.samic.data.entity.Profile;
import com.samic.samic.data.entity.User;
import com.samic.samic.views.meine_hardware.DataProvider;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserManagement extends VerticalLayout {
  private final DataProviderAdmin dataProvider;
  private final UserForm userForm;

  public UserManagement(DataProviderAdmin dataProvider, UserForm userForm) {
    this.dataProvider = dataProvider;
    this.userForm = userForm;
    User user = new User();
    user.setProfile(new Profile());
    userForm.setBean(user);
  }

  @PostConstruct
  private void initUI() {
    userForm.add( UIFactory.childContainer(JustifyContentMode.START,
            UIFactory.btnPrimary("Erstellen", e -> onCreate()),
            UIFactory.btnPrimary("Abbrechen", e -> onCancel())));
    add(UIFactory.rootComponentContainer("Benutzer anlegen",
    userForm
    ));

  }

  private void onCancel() {
    userForm.clearFields();
  }

  private void onCreate() {
    if (userForm.isValid()) {
      dataProvider.saveUser(userForm.saveBean());
      userForm.clearFields();
      UIFactory.NotificationSuccess("Benutzer angelegt").open();
    } else {
      UIFactory.NotificationError("Benutzer konnte nicht angelegt werden").open();
    }
  }
}
