package com.samic.samic.views.administration;

import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.Type;
import com.samic.samic.data.entity.User;
import com.samic.samic.data.fixture.Fixtures;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DataProviderAdmin {

  private List<User> users;
  private HashMap<String, String> appConfig = new HashMap<>();
  private List<NotificationLimit> notificationLimits;
  private List<Storage> storages;

  public DataProviderAdmin() {

  }


  public void saveReservationDuration(Integer value) {
    if (appConfig.containsKey("reservationMaxDuration")) {
      appConfig.replace("reservationMaxDuration", String.valueOf(value));
    } else {
      appConfig.put("reservationMaxDuration", String.valueOf(value));
    }
  }

  public Integer getReservationDuration() {
    if (appConfig.containsKey("reservationMaxDuration")) {
      return Integer.valueOf(appConfig.get("reservationMaxDuration"));
    } else {
      return 0;
    }
  }

  public List<NotificationLimit> getNotificationLimits() {
    notificationLimits = new ArrayList<>(List.of(
        NotificationLimit.builder()
            .type(Type.IP_PHONE)
            .minAmount(10)
            .maxAmount(20)
            .build(),
        NotificationLimit.builder()
            .type(Type.ROUTER)
            .maxAmount(30)
            .minAmount(10)
            .build(),
        NotificationLimit.builder()
            .type(Type.SFP)
            .maxAmount(100)
            .minAmount(10)
            .build(),
        NotificationLimit.builder()
            .type(Type.SUPPLY)
            .maxAmount(2000)
            .minAmount(500)
            .build(),
        NotificationLimit.builder()
            .type(Type.SWITCH)
            .maxAmount(300)
            .minAmount(100)
            .build()
    ));
    return notificationLimits;
  }

  public void saveNotificationLimit(NotificationLimit notificationLimit) {
    int index = notificationLimits.indexOf(notificationLimit);
    notificationLimits.remove(index);
    notificationLimits.add(index, notificationLimit);
  }

  public void removeNotificationLimit(NotificationLimit notificationLimit) {
    if (notificationLimits != null) {
      notificationLimits.remove(notificationLimit);
    }
  }

  public List<Storage> getStorages(int amount) {
    storages = new ArrayList<>(amount);

    for (int i = 0; i < amount; i++) {
      Storage storage = Fixtures.giveStorage1();
      storages.add(storage);
    }

    return storages;
  }

  public void saveStorage(Storage storage) {
    if (storages != null ) {
      int index = storages.indexOf(storage);
      storages.remove(index);
      storages.add(index, storage);
    }
  }

  public void removeStorage(Storage storage) {
    if (storages != null) {
      storages.remove(storage);
    }
  }

  public List<User> getUsers(int amount) {
    users = new ArrayList<>(amount+2);
    for (int i = 0; i < amount; i++) {
      User user = Fixtures.giveUser1();
      users.add(user);
    }
    return users;
  }

  public void saveUser(User user) {
    if (users != null) {
      int index = users.indexOf(user);
      if (index > -1) {
        users.remove(user);
        users.add(index, user);
      }
      user.setCreatedAt(LocalDateTime.now());
      user.setActivated(false);
      user.setLastLogin(LocalDateTime.now().minusYears(300));
      users.add(user);
    }
  }

  public void addUser(User user) {
    if (users != null) {
      users.add(user);
    }
  }

  public void removeUser(User user) {
    if (users != null) {
      users.remove(user);
    }
  }
}
