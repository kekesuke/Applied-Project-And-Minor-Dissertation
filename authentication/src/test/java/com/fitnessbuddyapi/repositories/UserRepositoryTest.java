package com.fitnessbuddyapi.repositories;

import com.fitnessbuddyapi.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldRetrieveUserByUsername() {
        //given
        String email = "test@test.com";
        String testUser = "test";

        User user = new User(testUser, email, "test");
        underTest.save(user);

        //when
        Optional<User> expected = underTest.findByUsername(testUser);
        //than
        assertThat(expected.isPresent()).isTrue();
    }


    @Test
    void itShouldCheckIfUserCannotBeRetrievedByUserName() {
        //given

        String testUser = "test";

        //when
        Optional<User> expected = underTest.findByUsername(testUser);

        //then
        assertThat(expected).isEmpty();
    }

    @Test
    void itShouldCheckWhenUserExistsByUsername() {
        //given
        String email = "test@test.com";
        String testUser = "test";

        User user = new User(testUser, email, "test");
        underTest.save(user);

        //when
        boolean expected = underTest.existsByUsername(testUser);
        //then
        assertThat(expected).isTrue();

    }

    @Test
    void itShouldCheckWhenUserDoesNotExistsByUsername() {
        //given
        String email = "test@test.com";
        String testUser = "test";

        User user = new User(testUser, email, "test");

        //when
        boolean expected = underTest.existsByUsername(testUser);
        //then
        assertThat(expected).isFalse();
    }

    @Test
    void itShouldCheckIfUserExistsByEmail() {
        //given
        String email = "test@test.com";
        String testUser = "test";

        User user = new User(testUser, email, "test");
        underTest.save(user);

        //when
        boolean expected = underTest.existsByEmail(email);
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfUserDoesNotExistsByEmail() {
        //given
        String email = "test@test.com";
        String testUser = "test";

        User user = new User(testUser, email, "test");

        //when
        boolean expected = underTest.existsByEmail(email);
        //then
        assertThat(expected).isFalse();
    }
}