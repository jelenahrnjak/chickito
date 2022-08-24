package com.ftn.Chickito.service;


import com.ftn.Chickito.dto.machineMaintenance.CreateMachineMaintenanceDto;
import com.ftn.Chickito.model.MachineMaintenance;

import java.util.List;

public interface MachineMaintenanceService {


    MachineMaintenance createMachineMaintenance(String authorUsername, CreateMachineMaintenanceDto createMaintenanceDto);
    List<MachineMaintenance> findAllByAuthor(String authorUsername);
    List<MachineMaintenance> findAllByDirector(String directorUsername);
}
