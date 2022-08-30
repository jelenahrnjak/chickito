package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.dto.user.UserViewDto;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.mapper.SectorMapper;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.model.WorkerOnMachine;
import com.ftn.Chickito.repository.UserRepository;
import com.ftn.Chickito.repository.WorkerOnMachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MachineMapperImpl implements MachineMapper {

    private final WorkerOnMachineRepository workerOnMachineRepository;
    private final UserRepository userRepository;
    @Override
    public MachineDto machineToMachineDto(Machine machine) {
        return MachineDto.builder()
                .id(machine.getId())
                .name(machine.getName())
                .model(machine.getModel())
                .serialNumber(machine.getSerialNumber())
                .technicalTask(machine.getTechnicalTask() == null ? "" : machine.getTechnicalTask())
                .sectorId(machine.getSector().getId())
                .sector(machine.getSector().getType().toString())
                .price(machine.getPrice())
                .quantity(machine.getQuantity())
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

    @Override
    public List<MachineDto> machineListToMachineDtoListWithWorker(List<Machine> machines, String workerUsername) {

        List<MachineDto> machinesRet = new ArrayList<>();

        for(Machine machine : machines){
            MachineDto dto = machineToMachineDto(machine);
            WorkerOnMachine workerOnMachine = workerOnMachineRepository.findByWorkerIdAndMachineId(getWorker(workerUsername).getId(), machine.getId());
            dto.setMainWorker(workerOnMachine.isMainWorker());
            machinesRet.add(dto);
        }
        return machinesRet;
    }

    private User getWorker(String username) {
        return userRepository.findByUsernameAndRoleName(username, "WORKER")
                .orElseThrow(() -> new EntityNotFoundException(String.format("Worker with username = %s doesn't exist.", username)));
    }
}
