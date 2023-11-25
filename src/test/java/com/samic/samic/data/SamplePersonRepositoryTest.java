package com.samic.samic.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SamplePersonRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void test(){
        User user = new User();

        var saved = userRepository.save(user);

        assertNotNull(saved.getId());
        assertEquals(user, saved);
    }

}