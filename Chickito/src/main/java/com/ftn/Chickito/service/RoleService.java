package com.ftn.Chickito.service;

import com.ftn.Chickito.model.Role;

public interface RoleService {
    Role findById(Long id);
    Role findByName(String name);
}
