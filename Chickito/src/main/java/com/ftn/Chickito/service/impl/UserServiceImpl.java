package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.mapper.UserMapper;
import com.ftn.Chickito.model.*;
import com.ftn.Chickito.model.enums.SectorType;
import com.ftn.Chickito.repository.*;
import com.ftn.Chickito.service.UserService;
import com.ftn.Chickito.service.WorkerOnMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final WorkerOnMachineService workerOnMachineService;
    private final SectorRepository sectorRepository;
    private final CompanyRepository companyRepository;
    public final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final static String WORKER_ROLE = "WORKER";
    private final static String LEADER_ROLE = "LEADER";
    private final static String DIRECTOR_ROLE = "DIRECTOR";

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseGet(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(UserRequest userRequest) {

        User u = this.userMapper.userRequestToUser(userRequest);


        if(userRequest.getRole() != 2){

            Sector sector = this.sectorRepository.findByCompanyAndType(userRequest.getCompanyId(), SectorType.values()[userRequest.getSector()]);
            u.setSector(sector);

        }

        User newUser = this.userRepository.save(u);

        if(userRequest.getRole() == 3){
            Sector sector = this.sectorRepository.findById(newUser.getSector().getId()).orElse(null);
            sector.setLeader(u);
            this.sectorRepository.save(sector);
        }

        if(userRequest.getRole() == 2) {

            Company company = this.companyRepository.findById(userRequest.getCompanyId()).orElseGet(null);
            company.setDirector(newUser);
            this.companyRepository.save(company);
        }
        return newUser;
    }

    @Override
    public List<User> findAllWorkersByLeader(String username) {

        User leader = getLeader(username);
        return userRepository.findAllBySectorAndRole(leader.getSector().getId(), WORKER_ROLE);
    }

    @Override
    public List<User> findAllByDirector(String username) {
        Company company = companyRepository.findByDirector(username);
        return userRepository.findAllByCompany(company.getId());
    }

    @Override
    public List<User> findAllByCompany(Long companyId) {
        return userRepository.findAllByCompany(companyId);
    }

    @Override
    public void delete(Long id) {
        User toDelete = this.userRepository.findById(id).orElseGet(null);
        if(toDelete == null){
            return;
        }
        toDelete.setDeleted(true);
        this.userRepository.save(toDelete);

    }

    @Override
    public void deleteCompanyUsers(Long id) {
        this.userRepository.findAllByCompany(id).forEach(user -> {
            workerOnMachineService.deleteWorker(user.getId());
            deleteCompanyUsers(user.getId());
        });
    }
    private User getWorker(String username) {
        return userRepository.findByUsernameAndRoleName(username, WORKER_ROLE)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Worker with username = %s doesn't exist.", username)));
    }
    private User getLeader(String username) {
        return userRepository.findByUsernameAndRoleName(username, LEADER_ROLE)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Leader with username = %s doesn't exist.", username)));
    }


    private User getDirector(String username) {
        return userRepository.findByUsernameAndRoleName(username, DIRECTOR_ROLE)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Director with username = %s doesn't exist.", username)));
    }

}
