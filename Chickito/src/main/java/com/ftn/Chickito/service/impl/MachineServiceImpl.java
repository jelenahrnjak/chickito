package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.machine.DocumentationDto;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.model.Company;
import com.ftn.Chickito.model.Documentation;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.repository.CompanyRepository;
import com.ftn.Chickito.repository.MachineRepository;
import com.ftn.Chickito.repository.UserRepository;
import com.ftn.Chickito.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final MachineMapper mapper;

    @Override
    public List<Machine> findAllByLeader(String leaderUsername) {
        User leader = userRepository.findByUsername(leaderUsername).orElse(null);

        return machineRepository.findAllBySector(leader.getSector().getId());
    }

    @Override
    public List<Machine> findAllByDirector(String directorUsername) {
        Company company = companyRepository.findByDirector(directorUsername);
        return machineRepository.findAllByCompany(company.getId());
    }

    @Override
    public Machine addDocumentation(Long machineId, DocumentationDto documentationDto) {

        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Machine with id = %s doesn't exist.", machineId)));

        machine.setDocumentation(mapper.documentationDtoToDocumentation(documentationDto));
        return machineRepository.save(machine);
    }
}
