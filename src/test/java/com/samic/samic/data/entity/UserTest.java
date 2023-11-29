package com.samic.samic.data.entity;

import com.samic.samic.data.foundation.DateTimeFactory;
import com.samic.samic.data.persistence.RepositoryUser;
import com.samic.samic.services.ServiceUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
//@Import(TestcontainersConfiguration.class)
class UserTest{

    @Autowired
    private RepositoryUser repository;
//    @Autowired
//    private ServiceUser serviceUser;

    @Test
    void ensureSaveAndReadWork(){
        //given
        User user = User.builder()
                            .role(Role.MANAGMENT)
//                            .hashedPassword("$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe")
                            .profile(Profile.builder().username("management").build()).build();

        User user2 = new User();
        //when

//        var saved = repository.saveAndFlush(user);
//        var saved = repositoryUser.findByProfile_Username("management");
//        var saved2 = serviceUser.findUser("management");
        var saved2 = repository.findByProfile_Username("management");
//        assertThat(saved).isNotNull().isSameAs(user);
        assertThat(saved2).isNotNull().isSameAs(user2);
    }

    @Autowired
    private RepositoryUser repositoryUser;
//    @Autowired
//    private ServiceUser    ServiceUser;

    @Test
    void test(){
        User user = new User();

//        var saved = ServiceUser.save(user);
        var saved = repositoryUser.save(user);

        assertNotNull(saved.getId());
//        assertEquals(user, saved);
    }
}