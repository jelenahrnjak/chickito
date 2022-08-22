package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.model.Machine;
import org.springframework.stereotype.Component;

@Component
public class MachineMapperImpl implements MachineMapper {

    @Override
    public MachineDto machineToMachineDto(Machine machine) {
        return MachineDto.builder()
                .id(machine.getId())
                .name(machine.getName())
                .model(machine.getModel())
                .serialNumber(machine.getSerialNumber())
                .documentation(machine.getDocumentation() == null ? "" : machine.getDocumentation().getText())
                .sectorId(machine.getSector().getId())
                .price(machine.getPrice())
                .build();
    }
}
