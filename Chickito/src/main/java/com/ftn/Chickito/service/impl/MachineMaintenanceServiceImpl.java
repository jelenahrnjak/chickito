package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.repository.MachineMaintenanceRepository;
import com.ftn.Chickito.service.MachineMaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MachineMaintenanceServiceImpl implements MachineMaintenanceService {

    private final MachineMaintenanceRepository machineMaintenanceRepository;
}
