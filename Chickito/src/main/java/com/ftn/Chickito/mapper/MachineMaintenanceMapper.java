package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.machineMaintenance.MachineMaintenanceViewDto;
import com.ftn.Chickito.model.MachineMaintenance;

import java.util.List;

public interface MachineMaintenanceMapper {

    MachineMaintenanceViewDto maintenanceToMaintenanceDto(MachineMaintenance machineMaintenance);
    List<MachineMaintenanceViewDto> maintenanceListToMaintenanceDtoList(List<MachineMaintenance> machineMaintenances);
}
