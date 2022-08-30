package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.dto.user.UserDto;
import com.ftn.Chickito.dto.user.UserViewDto;
import com.ftn.Chickito.exception.ResourceConflictException;
import com.ftn.Chickito.mapper.OrderMapper;
import com.ftn.Chickito.mapper.UserMapper;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.service.UserService;
import com.ftn.Chickito.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;
    private final TokenUtils tokenUtils;
    private static final String WHITESPACE = " ";

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto loadById(@PathVariable Long userId) {
        return mapper.userToUserDto(this.userService.findById(userId));
    }

    @GetMapping("/getCurrentUser")
    @PreAuthorize("isAuthenticated()")
    public UserDto getCurrentUser(@RequestHeader("Authorization") String jwtToken){

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);
        return mapper.userToUserDto(this.userService.findByUsername(username));
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> loadAll() {
        return mapper.userListToUserDtoList(this.userService.findAll());
    }

    @PostMapping
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

    @GetMapping("/findAllWorkersByLeader")
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity<List<UserViewDto>> findAllWorkersByLeader(@RequestHeader("Authorization") String jwtToken){

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(mapper.userListToUserViewDtoList(userService.findAllWorkersByLeader(username)), HttpStatus.OK);
    }

    @GetMapping("/findAllByDirector")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public ResponseEntity<List<UserViewDto>> findAllByDirector(@RequestHeader("Authorization") String jwtToken){

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(mapper.userListToUserViewDtoList(userService.findAllByDirector(username)), HttpStatus.OK);
    }

    @GetMapping("/findAllByCompany/{companyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserViewDto>> findAllByCompany(@PathVariable Long companyId){
        return new ResponseEntity<>(mapper.userListToUserViewDtoList(userService.findAllByCompany(companyId)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('DIRECTOR')")
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }

}
