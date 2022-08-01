package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.model.User;

public interface UserMapper {

    User userRequestToUser(UserRequest request);
}
