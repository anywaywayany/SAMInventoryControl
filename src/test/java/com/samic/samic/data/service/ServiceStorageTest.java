package com.samic.samic.data.service;

import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.fixture.Fixtures;
import com.samic.samic.services.ServiceStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ServiceStorageTest{

    @Autowired
    private ServiceStorage serviceStorage;


    //given
    @Test
    void ensure_storage_save_and_reload(){
        Storage storage = Fixtures.giveStorage1();

        //when
        var saved = serviceStorage.saveStorageByObject(storage);


        //then
        assertThat(serviceStorage.findStorageyID(saved.getId())).isSameAs(storage);
    }

}