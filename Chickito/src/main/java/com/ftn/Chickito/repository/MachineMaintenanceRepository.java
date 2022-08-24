package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.MachineMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MachineMaintenanceRepository extends JpaRepository<MachineMaintenance, Long> {

    @Query("select m from MachineMaintenance m where m.author.username = :authorUsername")
    List<MachineMaintenance> findAllByAuthor(String authorUsername);
    @Query("select m from MachineMaintenance m where m.author.sector.company.id = :companyId")
    List<MachineMaintenance> findAllByCompany(Long companyId);
}
