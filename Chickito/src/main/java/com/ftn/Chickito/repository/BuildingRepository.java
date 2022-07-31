package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Building;
import com.ftn.Chickito.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BuildingRepository extends JpaRepository<Building, Long> {

    Page<Building> findAll(Pageable pageable);
    @Query("select b from Building b where b.headOffice = true and b.company.id = :companyId")
    Building findHeadOfficeOfCompany(Long companyId);

    @Query("select b from Building b where b.company.id = :companyId")
    List<Building> findAllByCompany(Long companyId);
}
