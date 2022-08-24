package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.machineMaintenance.CreateMachineMaintenanceDto;
import com.ftn.Chickito.model.*;
import com.ftn.Chickito.repository.CompanyRepository;
import com.ftn.Chickito.repository.MachineMaintenanceRepository;
import com.ftn.Chickito.repository.MachineRepository;
import com.ftn.Chickito.repository.UserRepository;
import com.ftn.Chickito.service.MachineMaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MachineMaintenanceServiceImpl implements MachineMaintenanceService {

    private final MachineMaintenanceRepository machineMaintenanceRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final MachineRepository machineRepository;
    private final static String LEADER_ROLE = "LEADER";

    @Override
    public MachineMaintenance createMachineMaintenance(String authorUsername, CreateMachineMaintenanceDto createMaintenanceDto) {


        Set<MachineMaintenanceItem> items = new HashSet<>();
        User author = getLeader(authorUsername);

        createMaintenanceDto.getItems().forEach(itemDto -> {

            Machine machine = machineRepository.findById(itemDto.getMachine().getId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Machine with id = %s doesn't exist.", itemDto.getMachine().getId())));

            MachineMaintenanceItem machineMaintenanceItem = MachineMaintenanceItem.builder()
                    .machine(machine)
                    .plan(itemDto.getPlan())
                    .build();

            items.add(machineMaintenanceItem);
        });

        MachineMaintenance machineMaintenance = MachineMaintenance.builder()
                .author(author)
                .items(items)
                .startDate(createMaintenanceDto.getStartDate())
                .endDate(createMaintenanceDto.getEndDate())
                .build();

        items.forEach(item -> item.setMachineMaintenance(machineMaintenance));
        machineMaintenanceRepository.save(machineMaintenance);

        return machineMaintenance;

    }

    private User getLeader(String username) {
        return userRepository.findByUsernameAndRoleName(username, LEADER_ROLE)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Leader with username = %s doesn't exist.", username)));
    }

    @Override
    public List<MachineMaintenance> findAllByAuthor(String authorUsername) {
        return this.machineMaintenanceRepository.findAllByAuthor(authorUsername);
    }

    @Override
    public List<MachineMaintenance> findAllByDirector(String directorUsername) {
        Company company = this.companyRepository.findByDirector(directorUsername);
        return this.machineMaintenanceRepository.findAllByCompany(company.getId());
    }
}
