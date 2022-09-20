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

@RestController
public class Controller {

    private final ApplicationUserService userService;

    @Autowired
    public Controller(@Qualifier("applicationUserServiceImpl") ApplicationUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO userDTO) {

        User user = UserMapper.INSTANCE.dtoToUser(userDTO);

        userService.createUser(user);

        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

    @GetMapping("/info")
    @PreAuthorize(value = "hasAuthority('write')")
    public ResponseEntity<?> getInfo(Principal principal) {

        User user = userService.findByUsername(principal.getName());

        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

    @PatchMapping("/edit")
    @PreAuthorize(value = "hasAuthority('read')")
    public ResponseEntity<?> edit(@RequestBody UserDTO userDTO, Principal principal) {

        userService.update(principal, userDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('write')")
    public ResponseEntity<?> showUserInfo(@PathVariable int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize(value = "hasAuthority('write')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/ban/{id}")
    @PreAuthorize(value = "hasAuthority('write')")
    public ResponseEntity<HttpStatus> banUser(@PathVariable int id) {
        userService.banUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/unban/{id}")
    @PreAuthorize(value = "hasAuthority('write')")
    public ResponseEntity<HttpStatus> unbanUser(@PathVariable int id) {
        userService.unbanUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/makeAdmin/{id}")
    @PreAuthorize(value = "hasAuthority('write')")
    public ResponseEntity<HttpStatus> changeAuthorityToAdmin(@PathVariable int id) {
        userService.changeAuthorityToAdmin(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //<?> http response
    @PatchMapping("/makeUser/{id}")
    @PreAuthorize(value = "hasAuthority('write')")
    public ResponseEntity<HttpStatus> changeAuthorityToUser(@PathVariable int id) {
        userService.changeAuthorityToUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
