/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.user.service;

import com.example.user.domain.User;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rehan Ansari
 */
@Service
public interface UserService {

    User registerUser(User user);
    User getUserByUsername(String username);
    
}
