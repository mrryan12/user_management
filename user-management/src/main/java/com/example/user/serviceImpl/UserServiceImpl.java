/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.user.serviceImpl;

import com.example.user.domain.User;
import com.example.user.exception.InvalidFieldException;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rehan Ansari
 */
@Service
public class UserServiceImpl implements UserService {

     @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        validateUser(user);
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().length() < 3 || user.getUsername().length() > 20) {
            throw new InvalidFieldException("Username must be between 3 and 20 characters.");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new InvalidFieldException("Password must be at least 6 characters.");
        }

        if (user.getRole() == null || (!user.getRole().equals("ADMIN") && !user.getRole().equals("NORMAL"))) {
            throw new InvalidFieldException("Role must be either 'ADMIN' or 'NORMAL'.");
        }
    }

}
