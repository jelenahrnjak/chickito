package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.UserDto;
import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.mapper.AddressMapper;
import com.ftn.Chickito.mapper.UserMapper;
import com.ftn.Chickito.model.Role;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.model.enums.GenderType;
import com.ftn.Chickito.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AddressMapper addressMapper;

    @Override
    public User userRequestToUser(UserRequest userRequest) {

        User u = new User();
        u.setUsername(userRequest.getUsername());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setFirstName(userRequest.getFirstName());
        u.setEmail(userRequest.getEmail());
        u.setLastName(userRequest.getLastName());
        u.setActive(true);
        u.setDeleted(false);
        Role role = roleService.findById(userRequest.getRole());
        u.setRole(role);
        u.setGender(GenderType.values()[userRequest.getGender()]);
        u.setPhoneNumber(userRequest.getPhoneNumber());

        return u;
    }

    @Override
    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .address(addressMapper.addressToAddressDto(user.getAddress()))
                .build();
    }
}
