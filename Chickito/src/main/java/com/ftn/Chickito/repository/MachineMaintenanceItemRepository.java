package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.MachineMaintenance;
import com.ftn.Chickito.model.MachineMaintenanceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface MachineMaintenanceItemRepository extends JpaRepository<MachineMaintenanceItem, Long> {


    @Query("select m from MachineMaintenanceItem m where m.machineMaintenance.year = :year and m.machineMaintenance.author.username = :authorUsername")
    List<MachineMaintenanceItem> findAllByYearAndAuthor(String authorUsername, Integer year);
    @Query("select m from MachineMaintenanceItem m where m.machineMaintenance.year = :year and m.machineMaintenance.author.sector.id  = :sectorId")
    List<MachineMaintenanceItem> findAllByYearAndSector(Long sectorId, Integer year);
    @Query("select m from MachineMaintenanceItem m where m.machineMaintenance.year = :year and m.machine.id = :id")
    Set<MachineMaintenanceItem> findAllByYearAndMachine(Long id, Integer year);
}
