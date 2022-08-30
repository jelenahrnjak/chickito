package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.machine.DocumentationDto;
import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.dto.machine.SparePartDto;
import com.ftn.Chickito.dto.user.UserViewDto;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.mapper.SectorMapper;
import com.ftn.Chickito.model.*;
import com.ftn.Chickito.repository.UserRepository;
import com.ftn.Chickito.repository.WorkerOnMachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                .model(machine.getModel() == null ? "Nije naveden" : machine.getModel())
                .serialNumber(machine.getSerialNumber())
                .technicalTask(machine.getTechnicalTask() == null ? "" : machine.getTechnicalTask())
                .sectorId(machine.getSector().getId())
                .sector(machine.getSector().getType().toString())
                .price(machine.getPrice())
                .quantity(machine.getQuantity())
                .documentation(machine.getDocumentation() == null ? null : documentationToDocumentationDto(machine.getDocumentation()))
                .build();
    }

    private DocumentationDto documentationToDocumentationDto(Documentation documentation){
        return DocumentationDto.builder()
                .id(documentation.getId())
                .maintenanceInstructions(documentation.getMaintenanceInstructions())
                .washingInstructions(documentation.getWashingInstructions())
                .workInstructions(documentation.getWorkInstructions())
                .spareParts(sparePartsToSparePartsDto(documentation.getSpareParts()))
                .build();
    }

    private Set<SparePartDto> sparePartsToSparePartsDto(Set<SparePart> spareParts) {

        Set<SparePartDto> ret = new HashSet<>();
        spareParts.forEach(sparePart -> {
            ret.add(SparePartDto.builder()
                            .id(sparePart.getId())
                            .name(sparePart.getName())
                            .quantity(sparePart.getQuantity())
                            .stockNumber(sparePart.getStockNumber())
                            .build());
        });

        return ret;
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

    @Override
    public Documentation documentationDtoToDocumentation(DocumentationDto dto) {
        return Documentation.builder()
                .maintenanceInstructions(dto.getMaintenanceInstructions())
                .washingInstructions(dto.getWashingInstructions())
                .workInstructions(dto.getWorkInstructions())
                .spareParts(sparePartsDtoToSpareParts(dto.getSpareParts()))
                .build();
    }

    private Set<SparePart> sparePartsDtoToSpareParts(Set<SparePartDto> spareParts) {

        Set<SparePart> ret = new HashSet<>();
        spareParts.forEach(sparePart -> {
            ret.add(SparePart.builder()
                    .name(sparePart.getName())
                    .quantity(sparePart.getQuantity())
                    .stockNumber(sparePart.getStockNumber())
                    .build());
        });

        return ret;
    }

    private User getWorker(String username) {
        return userRepository.findByUsernameAndRoleName(username, "WORKER")
                .orElseThrow(() -> new EntityNotFoundException(String.format("Worker with username = %s doesn't exist.", username)));
    }
}
