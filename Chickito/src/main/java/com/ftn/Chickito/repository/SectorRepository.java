package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Sector;
import com.ftn.Chickito.model.enums.SectorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Query("select s from Sector s where s.company.id = :companyId and s.deleted = false")
    List<Sector> findByCompany(Long companyId);

    @Query("select s from Sector s where s.company.id = :companyId and s.type = :type and s.deleted = false")
    Sector findByCompanyAndType(Long companyId, SectorType type);

    @Query("select s from Sector s where s.company.director.username = :director and s.type = :type and s.deleted = false")
    Sector findByDirectorAndType(String director, SectorType type);
}
