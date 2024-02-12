package com.samic.samic.data.entity;

import com.samic.samic.data.fixture.Fixtures;
import org.junit.jupiter.api.Test;

//@DataJpaTest
class StorageObjectTest{


    @Test
    void ensureToStringworks(){
        //given
        CPE           cpe1           = Fixtures.giveCPE1();
        Producer      producer1       = Fixtures.giveProducer1();
        Storage       storage1       = Fixtures.giveStorage1();
        ObjectType    objectType     = Fixtures.giveObjectType1();
        StorageObject storageObject1 = Fixtures.giveStorageObject1();
        User          user1 = Fixtures.giveUser1();
        Reservation   reservation = Fixtures.giveReservation1();
        SFP           sfp = Fixtures.giveSFP1();
        storageObject1.setObjectTypeName(objectType);
        storageObject1.setStorage(storage1);
        storageObject1.setCpe(cpe1);


        System.out.println(sfp);
        System.out.println();
        System.out.println(reservation);
        System.out.println();
        System.out.println(user1);
        System.out.println();
        System.out.println(producer1);
        System.out.println();
        System.out.println(objectType);
        System.out.println();
        System.out.println(storageObject1);
        System.out.println();
        System.out.println(cpe1);


    }


}