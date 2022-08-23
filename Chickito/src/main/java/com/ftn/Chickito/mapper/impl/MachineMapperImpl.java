package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.model.Machine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<MachineDto> machineListToMachineDtoList(List<Machine> machines) {

        List<MachineDto> machinesRet = new ArrayList<>();

        for(Machine machine : machines){
            machinesRet.add(machineToMachineDto(machine));
        }
        return machinesRet;
    }
}
