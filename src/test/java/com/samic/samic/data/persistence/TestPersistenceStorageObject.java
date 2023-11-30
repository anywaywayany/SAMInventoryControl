package com.samic.samic.data.persistence;

import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.fixture.Fixtures;
import com.samic.samic.services.ServiceStorageObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestPersistenceStorageObject{

    @Autowired
    private ServiceStorageObject service;


    @Test
    void ensure_save_storageObject_into_DB(){

        //given
        StorageObject storageObject = Fixtures.giveStorageObject();

        //when
        var saved = service.saveStorageObject(storageObject);

        //then
        assertThat(service.findStorageObjectById(saved.getId()).getId())
                .isSameAs(storageObject.getId());
    }

    @Test
    void ensure_save_then_delete_object_from_db(){

        //given
        StorageObject storageObject = Fixtures.giveStorageObject();

        //when
        var saved = service.saveStorageObject(storageObject);

        //then
        service.deleteStorageObjectById(saved.getId());
        assertThat(service.doesObjectExistById(saved.getId())).isFalse();

    }

    @Test
    void ensure_save_and_delete_by_Object_and_check_if_exists(){
        //given
        StorageObject storageObject = Fixtures.giveStorageObject();

        //when
        var saved = service.saveStorageObject(storageObject);

        //then
        service.deleteByObject(saved);
        assertThat(service.doesObjectExistById(saved.getId())).isFalse();
    }
}
