package com.samic.samic.data.entity;

import com.samic.samic.data.service.RepositoryStorage;
import com.samic.samic.services.ServiceStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.utility.TestcontainersConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestcontainersConfiguration.class)
public class TestStorage{

    @Autowired
    RepositoryStorage repositoryStorage;
    @Autowired
    ServiceStorage    serviceStorage;

//    @Test
//    void ensure_storage_fetch_into_db(){
//
//        //given
//        Storage storage = Fixtures.giveStorage();
//
//
//        //when
////        var saved = repositoryStorage.save(storage);
//        Storage storage1 = serviceStorage.findStorageyID(storage.getId());
//        var saved1 = serviceStorage.saveStorageByObject(storage1);
//
//        //then
//        assertThat(serviceStorage.findStorageyID(saved1.getId())).isSameAs(storage1);
////        assertThat(repositoryStorage.findById(saved.getId()).get()).isSameAs(storage);
//
//    }
}
