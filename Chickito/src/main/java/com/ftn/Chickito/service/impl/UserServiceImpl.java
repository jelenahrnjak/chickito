package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.model.Role;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.model.enums.GenderType;
import com.ftn.Chickito.model.enums.SectorType;
import com.ftn.Chickito.repository.SectorRepository;
import com.ftn.Chickito.repository.UserRepository;
import com.ftn.Chickito.service.RoleService;
import com.ftn.Chickito.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final SectorRepository sectorRepository;
    public final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseGet(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(UserRequest userRequest) {

        User u = new User();
        u.setUsername(userRequest.getUsername());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        u.setFirstName(userRequest.getFirstName());
        u.setEmail(userRequest.getEmail());
        u.setLastName(userRequest.getLastName());
        u.setActive(true);
        u.setDeleted(false);
        u.setSector(sectorRepository.findByCompanyAndType(userRequest.getCompanyId(), SectorType.values()[userRequest.getSector()]));
        Role role = roleService.findById(userRequest.getRole());
        u.setRole(role);
        u.setGender(GenderType.values()[userRequest.getGender()]);


        return this.userRepository.save(u);
    }
}
