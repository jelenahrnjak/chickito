package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Long> {
}
