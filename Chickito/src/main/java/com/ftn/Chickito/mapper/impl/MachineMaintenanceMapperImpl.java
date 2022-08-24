package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.machineMaintenance.MachineMaintenanceItemDto;
import com.ftn.Chickito.dto.machineMaintenance.MachineMaintenanceViewDto;
import com.ftn.Chickito.mapper.MachineMaintenanceMapper;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.mapper.SectorMapper;
import com.ftn.Chickito.model.MachineMaintenance;
import com.ftn.Chickito.model.MachineMaintenanceItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MachineMaintenanceMapperImpl implements MachineMaintenanceMapper {

    private final MachineMapper machineMapper;
    private final SectorMapper sectorMapper;
    @Override
    public MachineMaintenanceViewDto maintenanceToMaintenanceDto(MachineMaintenance machineMaintenance) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return MachineMaintenanceViewDto.builder()
                .id(machineMaintenance.getId())
                .items(this.maintenanceItemsToMaintenanceItemsDto(machineMaintenance.getItems()))
                .author(machineMaintenance.getAuthor().getFirstName() + " " + machineMaintenance.getAuthor().getLastName())
                .sector(sectorMapper.sectorTypeToString(machineMaintenance.getAuthor().getSector().getType()))
                .startDate(machineMaintenance.getStartDate().format(formatter))
                .endDate(machineMaintenance.getEndDate().format(formatter))
                .build();
    }

    @Override
    public List<MachineMaintenanceViewDto> maintenanceListToMaintenanceDtoList(List<MachineMaintenance> machineMaintenances) {
        List<MachineMaintenanceViewDto> maintenanceViews = new ArrayList<>();
        for(MachineMaintenance maintenance : machineMaintenances){
            maintenanceViews.add(this.maintenanceToMaintenanceDto(maintenance));
        }

        return maintenanceViews;
    }

    private List<MachineMaintenanceItemDto> maintenanceItemsToMaintenanceItemsDto(Set<MachineMaintenanceItem> items){
        List<MachineMaintenanceItemDto> itemsDto = new ArrayList<>();

        for(MachineMaintenanceItem item : items){
            itemsDto.add(maintenanceItemToMaintenanceItemDto(item));
        }

        return itemsDto;
    }

    private MachineMaintenanceItemDto maintenanceItemToMaintenanceItemDto(MachineMaintenanceItem item) {

        return MachineMaintenanceItemDto.builder()
                .id(item.getId())
                .machine(machineMapper.machineToMachineDto(item.getMachine()))
                .plan(item.getPlan())
                .build();
    }
}
