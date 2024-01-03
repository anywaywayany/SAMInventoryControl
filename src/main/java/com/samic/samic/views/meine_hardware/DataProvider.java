package com.samic.samic.views.meine_hardware;

import com.samic.samic.components.UIFactory;
import com.samic.samic.data.entity.Customer;
import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.Type;
import com.samic.samic.data.entity.User;
import com.samic.samic.data.fixture.Fixtures;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DataProvider {

    List<Reservation> reservations;
    List<StorageObject> storageObjects;

    List<Storage> storages;

  public DataProvider () {
  }

  public List<Reservation> getReservations(int amount) {
    reservations = new ArrayList<>(amount);

    for (int i = 0; i <= amount; i++) {
      Reservation reservation = Fixtures.giveReservation1();
      reservation.setReservedFrom(User.builder().mail("user@email.com").build());
      reservation.setCustomer(Customer.builder().connectionNo(123129).build());
      reservation.setStorageObject(Fixtures.giveStorageObject1());
      reservation.getStorageObject().setObjectTypeName(ObjectType.builder().name("TestObjectname").build());
      reservations.add(reservation);
    }
    return reservations;
  }
  public List<StorageObject> getStorageObjects(int amount) {
    storageObjects = new ArrayList<>(amount);
    for (int i = 0; i <= amount; i++) {
      StorageObject storageObject = Fixtures.giveStorageObject1();
      if (i == amount) {
        storageObject.setCpe(null);
        storageObject.setSupply(null);
        storageObject.getSfp().setType(Type.SFP);
      } else if (i % 2 == 0) {
        storageObject.setCpe(null);
        storageObject.setSfp(null);
        storageObject.getSupply().setAmount(20);
      } else {
        storageObject.setSfp(null);
        storageObject.setSupply(null);
        storageObject.getCpe().setType(Type.IP_PHONE);
      }
      storageObjects.add(storageObject);
    }
    return storageObjects;
  }

  public List<Storage> getStorages(int amount) {
    storages = new ArrayList<>(amount);
    for (int i = 0; i <= amount; i++) {
      Storage storage = Fixtures.giveStorage1();

      storages.add(storage);
    }
    return storages;
  }

  public void remove(Reservation item) {
    if (reservations != null) {
      reservations.remove(item);
    }
  }

  public void remove(StorageObject storageObject) {
    if (storageObjects != null) {
      storageObjects.remove(storageObject);
    }
  }

  public void save(Reservation item) {
    if (reservations != null) {
      int index = reservations.indexOf(item);
      reservations.remove(index);
      reservations.add(index, item);
    }
  }

  public void save(StorageObject storageObject) {
    if (storageObjects != null) {
      int index = storageObjects.indexOf(storageObject);
      storageObjects.remove(index);
      storageObjects.add(index, storageObject);
    }
  }

}
