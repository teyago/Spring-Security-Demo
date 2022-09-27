package com.example.securitydemo.service;

import com.example.securitydemo.dto.UserDTO;
import com.example.securitydemo.entity.User;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * @author Goncharov Aleksandr
 */
@Service
public interface ApplicationUserService {
    User findById(int id);

    User findByUsername(String username);

    void createUser(User user);

    void deleteUser(int id);

    void banUser(int id);

    void unbanUser(int id);

    void changeAuthorityToAdmin(int id);

    void changeAuthorityToUser(int id);

    void update(Principal principal, UserDTO userDTO);

    void update(int id, UserDTO userDTO);

}
