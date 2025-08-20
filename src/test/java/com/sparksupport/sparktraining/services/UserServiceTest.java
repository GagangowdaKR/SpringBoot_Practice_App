package com.sparksupport.sparktraining.services;

import com.sparksupport.sparktraining.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @BeforeAll
    public static void setup(){
        System.out.println("!!! Before All");
    }

    @BeforeEach
    public void setUpEachTest(){
        System.out.println("! Before Each");
    }

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void getUserNameByIdTest() {
        Integer userId = 1;
        
        when(userRepository.findNameById(userId)).thenReturn("Gagan Test Name");

        String foundName = userService.getUserNameById(userId);

        System.out.println("I am in getUserNameById test method");

        assertEquals(foundName, "Gagan Test Name");
    }

    @AfterAll
    public static void destroy(){
        System.out.println("After All !!!");
    }

    @AfterEach
    public void cleanup(){
        System.out.println("After Each !");
    }
}
