package com.samic.samic.data;

import com.samic.samic.data.entity.User;
import com.samic.samic.data.persistence.RepositoryUser;
import com.samic.samic.services.ServiceUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class SamplePersonRepositoryTest {

    @Autowired
    private RepositoryUser userRepository;
    @Autowired
    private ServiceUser serviceUser;

    @Test
    void test(){
        User user = new User();

        var saved = serviceUser.save(user);

        assertNotNull(saved.getId());
        assertEquals(user, saved);
    }

}