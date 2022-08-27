package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.UserDto;
import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.dto.workerOnMachine.WorkerDto;
import com.ftn.Chickito.model.User;

import java.util.List;

public interface UserMapper {

    User userRequestToUser(UserRequest request);
    UserDto userToUserDto(User user);
    List<UserDto> userListToUserDtoList(List<User> users);
    WorkerDto userToWorkerDto(User user);
    List<WorkerDto> userListToWorkerDtoList(List<User> users, Long machineId);
}
