package com.samic.samic.data.service;

import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.fixture.Fixtures;
import com.samic.samic.services.ServiceObjectType;
import com.samic.samic.services.ServiceStorageObject;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@DataJpaTest
public class TestPersistenceStorageObject{

    @Autowired
    private ServiceStorageObject serviceStorageObject;
    @Autowired
    private ServiceObjectType serviceObjectType;

    @Test
    void ensure_save_storageObject_into_DB(){

        //given
        StorageObject storageObject = Fixtures.giveStorageObject1();

        //when
        var saved = serviceStorageObject.saveStorageObject(storageObject);

        //then
        assertThat(serviceStorageObject.findStorageObjectById(saved.getId()).getId())
                .isSameAs(storageObject.getId());
    }

    //    @Test
    //    void ensure_save_then_delete_object_from_db(){
    //
    //        //given
    //        StorageObject storageObject = Fixtures.giveStorageObject();
    //
    //        //when
    //        var saved = service.saveStorageObject(storageObject);
    //
    //        //then
    //        service.deleteStorageObjectById(saved.getId());
    //        assertThat(service.doesObjectExistById(storageObject.getId())).isFalse();
    //
    //    }
    //
    //    @Test
    //    void ensure_save_and_delete_by_Object_and_check_if_exists(){
    //        //given
    //        StorageObject storageObject = Fixtures.giveStorageObject();
    //
    //        //when
    //        var saved = service.saveStorageObject(storageObject);
    //
    //        //then
    //        service.deleteByObject(saved);
    //        assertThat(service.doesObjectExistById(saved.getId())).isFalse();
    //    }

    @Test
    void ensure_save_ObjectType_into_StorageObject(){
        //given
        ObjectType    objectType1   = ObjectType.builder().name("AABBCCDD112").build();
        StorageObject storageObject = Fixtures.giveStorageObject1();
        storageObject.setObjectTypeName(objectType1);

        //when
        var saved = serviceStorageObject.saveStorageObject(storageObject);

        //then
        assertThat(serviceStorageObject.findStorageObjectById(storageObject.getId()).getObjectTypeName()).isSameAs(objectType1);
    }

    @Test
    void set_storageType_to_storageObject_and_save_to_DB_then_fetch(){

        //given
        ObjectType    objectType1   = Fixtures.giveObjectType1();
        StorageObject storageObject = Fixtures.giveStorageObject3();
        log.debug("declared and initiated {}, {} ".formatted(objectType1, storageObject));

        storageObject.setObjectTypeName(objectType1);
        log.debug("set ObjectType:{} to storageObject: {} ".formatted(objectType1, storageObject));

        //when
        var saved = serviceStorageObject.saveStorageObject(storageObject);
        log.debug("saved ObjectType:{} composed wiht storageObject: {} into db ".formatted(objectType1, storageObject));

        //then
        assertThat(serviceStorageObject.findStorageObjectById(saved.getId())).isSameAs(storageObject);
        log.debug("ensured fetching from db ObjectType:{} composed wiht storageObject: {} from db ".formatted(objectType1, storageObject));

    }

    @Test
    void ensure_setting_objectType_to_storageObject_from_a_existing_objectType_in_DB(){
        /*
        1.Part: saving ObjectType into DB and ensuring existing.
         */
        //given 1.0 (Set up objectType via GUI)

        ObjectType        objectType1       = Fixtures.giveObjectType1();

        //when 1.1 (save objectType set before into DB, should be visible on dopdown-menu)
        var saved = serviceObjectType.saveObjectTypeByObject(objectType1);

        //then 1.2 (just controlling availability, when saving into DB was successful before)
        if(serviceObjectType.doesObjectExistById(objectType1.getId())){
            //create storageObject (create storageObject in GUI)
            StorageObject storageObject1 = Fixtures.giveStorageObject3();
            //get ObjectType from DB to set (choose objectType from DB that is visible in dropdown-menu that was saved before)
            ObjectType objectType1Fetched = serviceObjectType.findObjectTypeByID(objectType1.getId());

            //ensure fetched objectType same as safed before (quick ensure if its really the saved objectType )
            if(objectType1Fetched.equals(objectType1)){
                //set the fetched objectType to storageObject
                //given 2.0 (when its choosable in dropdown-menu, then set it to the created storageObject)
                storageObject1.setObjectTypeName(objectType1Fetched);

                //save storageObject with bounded objectType to DB
                //when 2.1 (save the bounded couple into DB. objectType that exists before in DB and storageObject that is going to be creationg)
                var savedStorageObjectWitBoundedObjectType = serviceStorageObject.saveStorageObject(storageObject1);

                //ensure saving storageObject with objectType set on it
                //then 2.2 (gettin objectType from saved storageObject and ensure if its the same objectType that was choosen before for coupling together)
                assertThat(serviceStorageObject.findStorageObjectById(savedStorageObjectWitBoundedObjectType.getId())
                                   .getObjectTypeName()).isSameAs(objectType1);
                assertThat(serviceStorageObject.findStorageObjectById(savedStorageObjectWitBoundedObjectType.getId())).isSameAs(storageObject1);
            }

        }
    }
}
