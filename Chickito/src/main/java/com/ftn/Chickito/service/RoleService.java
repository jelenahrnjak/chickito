package com.ftn.Chickito.service;

import com.ftn.Chickito.model.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id);
    Role findByName(String name);
}
