package com.ftn.Chickito.controller;

import java.util.List;

import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.exception.ResourceConflictException;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User loadById(@PathVariable Long userId) {
        return this.userService.findById(userId);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> loadAll() {
        return this.userService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {

        User existUser = this.userService.findByUsername(userRequest.getUsername());

        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Korisničko ime već postoji!");
        }

        User existUserEmail = this.userService.findByEmail(userRequest.getEmail());

        if (existUserEmail != null) {
            throw new ResourceConflictException(userRequest.getId(), "Email već postoji!");
        }

        User user = this.userService.save(userRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
