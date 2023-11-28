package com.samic.samic.data.entity;

import com.samic.samic.data.foundation.DateTimeFactory;
import com.samic.samic.data.persistence.RepositoryUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserTest{

    @Autowired
    private RepositoryUser repository;

    @Test
    void ensureSaveAndReadWork(){
        //given
        User user = User.builder()
                            .storageObject((List<StorageObject>) StorageObject.builder()
                                                   .name("name02")
                                                   .remark("blablabal")
                                                   .build())
                            .profile(Profile.builder().username("Mikey").build())
                            .profile(Profile.builder().firstName("Mouse").build())
                            .profile(Profile.builder().lastName("Mighty").build())
                            .profile(Profile.builder().phone("03939383828").build())
                            .hashedPassword("geheimZiffernNurzweiOdermachenwirnureines")
                            .role(Role.MANAGMENT)
                            .createdAt(DateTimeFactory.now_minus_one_year())
                            .lastLogin(DateTimeFactory.now_minus_one_week())
                            .activated(true)
                            .build();
        //when

        var saved = repository.saveAndFlush(user);

        assertThat(saved).isNotNull().isSameAs(user);
    }
}