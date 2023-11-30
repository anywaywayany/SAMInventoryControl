package com.samic.samic.data.entity;

import com.samic.samic.data.fixture.Fixtures;
import com.samic.samic.data.persistence.RepositoryStorage;
import org.junit.jupiter.api.Test;
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

    @Test
    void ensure_storage_fetch_into_db(){

        //given
        Storage storage = Fixtures.giveStorage();

        //when
        var saved = repositoryStorage.save(storage);

        //then
        assertThat(repositoryStorage.findById(saved.getId()).get()).isSameAs(storage);

    }
}
