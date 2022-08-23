package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByName(String name);
    Company findByPib(String pib);

    @Query("select c from Company c where c.deleted = false")
    List<Company> findAllNotDeleted();

    @Query("select c from Company c where c.director.username = :directorUsername")
    Company findByDirector(String directorUsername);
}
