/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.user.serviceImpl;

import com.example.user.domain.User;
import com.example.user.exception.InvalidFieldException;
import com.example.user.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Rehan Ansari
 */
@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


 @Test
    void testRegisterUser_ValidUser() {
       
        User user = new User(1L,"testuser", "password123", "NORMAL");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User registeredUser = userService.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
        assertEquals("password123", registeredUser.getPassword());
        assertEquals("NORMAL", registeredUser.getRole());
    }

    @Test
    void testRegisterUser_InvalidUsername() {
        User user = new User(1L, "us", "password123", "NORMAL");
        assertThrows(InvalidFieldException.class, () -> userService.registerUser(user));
    }

    @Test
    void testRegisterUser_InvalidRole() {
        User user = new User(1L, "testuser", "password123", "INVALID_ROLE");
        assertThrows(InvalidFieldException.class, () -> userService.registerUser(user));
    }

    @Test
    void testRegisterUser_InvalidPassword() {
        User user = new User(1L, "testuser", "pass", "NORMAL");
        assertThrows(InvalidFieldException.class, () -> userService.registerUser(user));
    }

}
