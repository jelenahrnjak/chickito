package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.machine.DocumentationDto;
import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.model.Documentation;
import com.ftn.Chickito.model.Machine;

import java.util.List;

public interface MachineMapper {

    MachineDto machineToMachineDto(Machine machine);
    List<MachineDto> machineListToMachineDtoList(List<Machine> machines);
    List<MachineDto> machineListToMachineDtoListWithWorker(List<Machine> machines, String workerUsername);
    Documentation documentationDtoToDocumentation(DocumentationDto documentationDto);

}
