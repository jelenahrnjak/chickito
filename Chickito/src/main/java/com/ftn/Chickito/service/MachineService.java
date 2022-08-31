package com.ftn.Chickito.service;

import com.ftn.Chickito.dto.machine.DocumentationDto;
import com.ftn.Chickito.dto.machine.MachineBaseDto;
import com.ftn.Chickito.model.Machine;

import java.util.List;

public interface MachineService {

    List<Machine> findAllByLeader(String leaderUsername);

    List<Machine> findAllByDirector(String username);

    Machine addDocumentation(Long machineId, DocumentationDto documentationDto);

    Machine editMachine(Long machineId, MachineBaseDto editDto);

    void delete(Long id);
}
