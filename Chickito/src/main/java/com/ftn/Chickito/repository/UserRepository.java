package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndRoleName(String username, String roleName);
    User findByEmail(String email);

    Optional<User> findByIdAndRoleName(Long id, String workerRole);

    @Query("select u from User u where u.sector.id = :sectorId and u.role.name = :roleName and u.deleted = false")
    List<User> findAllBySectorAndRole(Long sectorId, String roleName);

    @Query("select u from User u where u.sector.id = :sectorId and u.role.name not like 'ADMIN' and u.deleted = false ")
    List<User> findAllBySector(Long sectorId);

    @Query("select u from User u where u.sector.company.id = :companyId and u.role.name not like 'ADMIN' and u.deleted = false")
    List<User> findAllByCompany(Long companyId);
}
