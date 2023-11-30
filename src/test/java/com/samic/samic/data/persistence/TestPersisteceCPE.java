package com.samic.samic.data.persistence;


import com.samic.samic.data.entity.CPE;
import com.samic.samic.data.fixture.Fixtures;
import com.samic.samic.services.ServiceCPE;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestPersisteceCPE{

    @Autowired
    private ServiceCPE service;

    @Test
    void ensure_save_cpe_into_DB_and_find(){

        //given
        CPE cpe = Fixtures.giveCPE1();

        //when
        var saved = service.saveCPEByObject(cpe);

        //then
        assertThat(service.findCPEByID(saved.getId()).getId())
                .isSameAs(cpe.getId());
    }

    @Test
    void ensure_save_and_delete_by_id(){
        //given
        CPE cpe = Fixtures.giveCPE1();
        CPE cpe2 = Fixtures.giveCPE2();

        //when
        var saved = service.saveCPEByObject(cpe);

        //then
        service.deleteCPEtById(saved.getId());
        assertThat(service.doesObjectExistById(saved.getId())).isFalse(); //TODO 1 , check producer to
    }

    @Test
    void ensure_save_and_delete_by_Object_and_check_if_exists(){
        //given
        CPE cpe = Fixtures.giveCPE1();

        //when
        var saved = service.saveCPEByObject(cpe);

        //then
        if(service.findCPEByID(saved.getId()).equals(cpe.getId())){
            service.deleteByObject(saved);
            assertThat(service.doesObjectExistById(cpe.getId())).isFalse();
        }
    }
}
