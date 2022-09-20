package com.example.securitydemo.service;

import com.example.securitydemo.dto.UserDTO;
import com.example.securitydemo.entity.Role;
import com.example.securitydemo.entity.Status;
import com.example.securitydemo.entity.User;
import com.example.securitydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.security.Principal;

@Component
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("sad"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("sad"));
    }

    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        saveUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void banUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("sad"));
        user.setStatus(Status.BANNED);
    }

    @Override
    @Transactional
    public void unbanUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("sad"));
        user.setStatus(Status.ACTIVE);
    }

    @Override
    @Transactional
    public void changeAuthorityToAdmin(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("sad"));
        user.setRole(Role.ADMIN);
    }

    @Override
    @Transactional
    public void changeAuthorityToUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("sad"));
        user.setRole(Role.USER);
    }

    @Override
    @Transactional
    public void update(Principal principal, UserDTO userDTO) {

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("sad"));

        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }
        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
