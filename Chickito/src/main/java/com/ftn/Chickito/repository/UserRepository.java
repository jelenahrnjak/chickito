package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndRoleName(String username, String roleName);
    User findByEmail(String email);
}
