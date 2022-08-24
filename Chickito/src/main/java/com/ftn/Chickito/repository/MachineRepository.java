package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Long> {

    @Query("select m from Machine m where m.sector.id = :sectorId and m.active = true")
    List<Machine> findAllBySector(Long sectorId);

    @Query("select m from Machine m where m.sector.company.id = :companyId and m.active = true")
    List<Machine> findAllByCompany(Long companyId);
}
