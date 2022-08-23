package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.model.Machine;

import java.util.List;

public interface MachineMapper {

    MachineDto machineToMachineDto(Machine machine);
    List<MachineDto> machineListToMachineDtoList(List<Machine> machines);
}
