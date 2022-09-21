package com.example.securitydemo.controllers;

import com.example.securitydemo.dto.UserDTO;
import com.example.securitydemo.entity.User;
import com.example.securitydemo.service.ApplicationUserService;
import com.example.securitydemo.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Goncharov Aleksandr
 */
@RestController
public class UserController {

    private final ApplicationUserService userService;

    @Autowired
    public UserController(@Qualifier("applicationUserServiceImpl") ApplicationUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO userDTO) {

        User user = UserMapper.INSTANCE.dtoToUser(userDTO);

        userService.createUser(user);

        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

    @PatchMapping("/edit")
    @PreAuthorize(value = "hasAuthority('read')")
    public ResponseEntity<?> edit(@RequestBody UserDTO userDTO, Principal principal) {

        userService.update(principal, userDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/info")
    @PreAuthorize(value = "hasAuthority('read')")
    public ResponseEntity<?> getInfo(Principal principal) {

        User user = userService.findByUsername(principal.getName());

        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }
}
