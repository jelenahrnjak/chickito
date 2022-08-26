package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.machineMaintenance.MachineMaintenanceItemDto;
import com.ftn.Chickito.dto.machineMaintenance.MachineMaintenanceViewDto;
import com.ftn.Chickito.dto.machineMaintenance.MaintenanceReportDto;
import com.ftn.Chickito.mapper.AddressMapper;
import com.ftn.Chickito.mapper.MachineMaintenanceMapper;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.mapper.SectorMapper;
import com.ftn.Chickito.model.Building;
import com.ftn.Chickito.model.MachineMaintenance;
import com.ftn.Chickito.model.MachineMaintenanceItem;
import com.ftn.Chickito.repository.BuildingRepository;
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
    private final AddressMapper addressMapper;
    private final BuildingRepository buildingRepository;
    @Override
    public MachineMaintenanceViewDto maintenanceToMaintenanceDto(MachineMaintenance machineMaintenance) {

        return MachineMaintenanceViewDto.builder()
                .id(machineMaintenance.getId())
                .items(this.maintenanceItemsToMaintenanceItemsDto(machineMaintenance.getItems()))
                .author(machineMaintenance.getAuthor().getFirstName() + " " + machineMaintenance.getAuthor().getLastName())
                .sector(sectorMapper.sectorTypeToString(machineMaintenance.getAuthor().getSector().getType()))
                .year(machineMaintenance.getYear())
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

    @Override
    public MaintenanceReportDto maintenanceToMaintenanceReportDto(MachineMaintenance maintenance) {

        Building headOffice = this.buildingRepository.findHeadOfficeOfCompany(maintenance.getAuthor().getSector().getCompany().getId());

        return MaintenanceReportDto.builder()
                .companyName(maintenance.getAuthor().getSector().getCompany().getName())
                .author("Rukovodilac: " + maintenance.getAuthor().getFirstName() + " " + maintenance.getAuthor().getLastName())
                .sector("Sektor: " + sectorMapper.sectorTypeToString( maintenance.getAuthor().getSector().getType()))
                .headOffice(headOffice == null ? "" : "Sedište: " + this.addressMapper.getAddressString(headOffice.getAddress()))
                .title("Plan održavanja za " + maintenance.getYear().toString() + ". godinu")
                .year(maintenance.getYear())
                .build();
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
