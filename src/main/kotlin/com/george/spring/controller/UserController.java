package com.george.spring.controller;

import com.george.spring.model.User;
import com.george.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired UserService service;


    // hasRole('ROLE_') haseAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')
    // and you should to add @EnableGlobalMethodSecurity(prePostEnabled = true) in the SecurityConfig class

    // @GetMapping
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // ResponseEntity<?> call() { return ResponseEntity.ok().body(?); }

    // @GetMapping
    // @PreAuthorize("haseAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STUDENT')")
    // ResponseEntity<?> call() { return ResponseEntity.ok().body(?); }

    // @GetMapping
    // @PreAuthorize("hasAuthority('student:read')")
    // ResponseEntity<?> call() { return ResponseEntity.ok().body(?); }

    // @GetMapping
    // @PreAuthorize("hasAnyAuthority('student:read', 'student:write')")
    // ResponseEntity<?> call() { return ResponseEntity.ok().body(?); }

    @GetMapping
    ResponseEntity<List<User>> getAll() {
        List<User> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("{userId}")
    ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        User user = service.findUser(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = service.createUser(user);
        return ResponseEntity.ok().body(createdUser);
    }

}

