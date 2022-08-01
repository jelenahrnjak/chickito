package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.mapper.UserMapper;
import com.ftn.Chickito.model.*;
import com.ftn.Chickito.model.enums.GenderType;
import com.ftn.Chickito.model.enums.SectorType;
import com.ftn.Chickito.repository.*;
import com.ftn.Chickito.service.RoleService;
import com.ftn.Chickito.service.SectorService;
import com.ftn.Chickito.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final LeaderRepository leaderRepository;
    private final DirectorRepository directorRepository;
    private final WorkerRepository workerRepository;
    private final SectorRepository sectorRepository;
    public final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseGet(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
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


        if(userRequest.getRole() == 2){

            Director newUser = new Director(u);
            return this.directorRepository.save(newUser);

        }else if(userRequest.getRole() == 3){

            Leader newUser = new Leader(u);
            Sector sector = sectorRepository.findByCompanyAndType(userRequest.getCompanyId(), SectorType.values()[userRequest.getSector()]);
            newUser.setSector(sector);
            this.leaderRepository.save(newUser);
            sector.setLeader(newUser);
            this.sectorRepository.save(sector);
            return newUser;
        }


        Worker newUser = new Worker(u);
        Sector sector = sectorRepository.findByCompanyAndType(userRequest.getCompanyId(), SectorType.values()[userRequest.getSector()]);
        newUser.setSector(sector);
        return this.workerRepository.save(newUser);
    }
}
