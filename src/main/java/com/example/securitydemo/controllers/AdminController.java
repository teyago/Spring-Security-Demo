package com.example.securitydemo.controllers;

import com.example.securitydemo.dto.UserDTO;
import com.example.securitydemo.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Goncharov Aleksandr
 */
@RestController
@RequestMapping("/adminpanel")
public class AdminController {

    private final ApplicationUserService userService;

    @Autowired
    public AdminController(@Qualifier("applicationUserServiceImpl") ApplicationUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info/{id}")
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

    @PatchMapping("/makeUser/{id}")
    @PreAuthorize(value = "hasAuthority('write')")
    public ResponseEntity<HttpStatus> changeAuthorityToUser(@PathVariable int id) {
        userService.changeAuthorityToUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    @PreAuthorize(value = "hasAuthority('write')")
    public ResponseEntity<?> edit(@RequestBody UserDTO userDTO, @PathVariable int id) {

        userService.update(id, userDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
