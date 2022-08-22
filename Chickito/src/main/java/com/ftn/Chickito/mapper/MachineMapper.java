package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.model.Machine;

public interface MachineMapper {

    MachineDto machineToMachineDto(Machine machine);
}
