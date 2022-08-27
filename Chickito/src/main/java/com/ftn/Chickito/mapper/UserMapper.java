package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.user.UserDto;
import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.dto.user.UserViewDto;
import com.ftn.Chickito.model.User;

import java.util.List;

public interface UserMapper {

    User userRequestToUser(UserRequest request);
    UserDto userToUserDto(User user);
    List<UserDto> userListToUserDtoList(List<User> users);
    UserViewDto userToUserViewDto(User user);
    List<UserViewDto> userListToWorkerOnMachineDtoList(List<User> users, Long machineId);
    List<UserViewDto> userListToUserViewDtoList(List<User> users);
}
