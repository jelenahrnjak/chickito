package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.machineMaintenance.MachineMaintenanceItemDto;
import com.ftn.Chickito.dto.machineMaintenance.MachineMaintenanceViewDto;
import com.ftn.Chickito.dto.machineMaintenance.MaintenanceReportDto;
import com.ftn.Chickito.model.MachineMaintenance;
import com.ftn.Chickito.model.MachineMaintenanceItem;

import java.util.List;
import java.util.Set;

public interface MachineMaintenanceMapper {

    MachineMaintenanceViewDto maintenanceToMaintenanceDto(MachineMaintenance machineMaintenance);
    List<MachineMaintenanceViewDto> maintenanceListToMaintenanceDtoList(List<MachineMaintenance> machineMaintenances);
    MaintenanceReportDto maintenanceToMaintenanceReportDto(MachineMaintenance maintenance);

    List<MachineMaintenanceItemDto> maintenanceItemsToMaintenanceItemsDto(Set<MachineMaintenanceItem> allByWorker);
}
