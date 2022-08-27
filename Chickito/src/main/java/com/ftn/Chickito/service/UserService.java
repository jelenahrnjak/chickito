package com.ftn.Chickito.service;

import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll ();
    User save(UserRequest userRequest);
    List<User> findAllWorkersBySector(Long sectorId);
    List<User> findAllBySector(Long sectorId);
    List<User> findAllByCompany(Long companyId);
    void delete(Long id);

    void deleteCompanyUsers(Long id);
}
