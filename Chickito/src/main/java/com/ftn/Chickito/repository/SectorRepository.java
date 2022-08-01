package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Query("select s from Sector s where s.company.id = :companyId and s.deleted = false")
    List<Sector> findByCompany(Long companyId);
}
