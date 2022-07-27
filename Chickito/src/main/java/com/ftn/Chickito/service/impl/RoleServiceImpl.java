package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.model.Role;
import com.ftn.Chickito.repository.RoleRepository;
import com.ftn.Chickito.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findById(Long id) {
        Role auth = this.roleRepository.getOne(id);
        return auth;
    }

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
