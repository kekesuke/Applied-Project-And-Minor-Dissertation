package com.fitnessbuddyapi.services;

import com.fitnessbuddy.amqp.RabbitMQMessageProducer;
import com.fitnessbuddy.clients.fraud.FraudClient;
import com.fitnessbuddy.clients.fraud.response.FraudCheckResponse;
import com.fitnessbuddyapi.models.Role;
import com.fitnessbuddyapi.models.User;
import com.fitnessbuddyapi.repositories.RoleRepository;
import com.fitnessbuddyapi.repositories.UserRepository;
import com.fitnessbuddyapi.security.jwt.JwtUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import com.fitnessbuddyapi.payload.request.SignupRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class UserDetailsServiceImplTest {
    private AutoCloseable autoCloseable;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private FraudClient fraudClient;
    @Mock
    private RabbitMQMessageProducer rabbitMQMessageProducer;


    private UserDetailsServiceImpl underTest;

    private static final String USER_EMAIL = "test@test.com";
    private static final String TEST_USER = "test";
    private static final String USER_PASSWORD = "test";

    UserDetailsServiceImplTest() {
    }

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserDetailsServiceImpl(userRepository, roleRepository, encoder, authenticationManager, jwtUtils, fraudClient, rabbitMQMessageProducer);
    }


    @AfterEach //close the resource after the test
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void CanGetUserDetailsByUsername() {
        //given
        User user = new User(TEST_USER, USER_EMAIL, USER_PASSWORD);
        //when
        Mockito.when(userRepository.findByUsername(TEST_USER)).thenReturn(Optional.of(user));

        //actual
        assertThat(underTest.loadUserByUsername(TEST_USER)).isEqualTo(UserDetailsImpl.build(user));
    }

    @Test

    void registerUserWithDefaultRole() {
        //given
        SignupRequest signupRequest = mock(SignupRequest.class);
        Errors errors = mock(Errors.class);
        Role userRole = mock(Role.class);

        User user = new User(TEST_USER, USER_EMAIL, USER_PASSWORD);

        //when
        Mockito.when(fraudClient.isFraud(Mockito.any(), Mockito.any())).thenReturn(new FraudCheckResponse(false));
        underTest.registerUserWithDefaultRole(signupRequest, errors);
        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(signupRequest);
        //doReturn(registered).when(underTest.registerUserWithDefaultRole(any(SignupRequest.class), errors));
    }

    @Test
    @Disabled
    void authenticateUser() {
    }
}