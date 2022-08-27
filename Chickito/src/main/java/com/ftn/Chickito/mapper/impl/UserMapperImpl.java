package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.auth.UserRequest;
import com.ftn.Chickito.dto.user.UserDto;
import com.ftn.Chickito.dto.user.UserViewDto;
import com.ftn.Chickito.mapper.AddressMapper;
import com.ftn.Chickito.mapper.SectorMapper;
import com.ftn.Chickito.mapper.UserMapper;
import com.ftn.Chickito.model.Role;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.model.VacationDay;
import com.ftn.Chickito.model.WorkerOnMachine;
import com.ftn.Chickito.model.enums.GenderType;
import com.ftn.Chickito.repository.WorkerOnMachineRepository;
import com.ftn.Chickito.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AddressMapper addressMapper;
    private final SectorMapper sectorMapper;
    private final WorkerOnMachineRepository workerOnMachineRepository;

    @Override
    public User userRequestToUser(UserRequest userRequest) {

        User u = new User();
        u.setUsername(userRequest.getUsername());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setFirstName(userRequest.getFirstName());
        u.setEmail(userRequest.getEmail());
        u.setLastName(userRequest.getLastName());
        u.setActive(true);
        u.setDeleted(false);
        Role role = roleService.findById(userRequest.getRole());
        u.setRole(role);
        u.setGender(GenderType.values()[userRequest.getGender()]);
        u.setPhoneNumber(userRequest.getPhoneNumber());
        u.setAvailableVacationDays(calculateVacationDaysForThisYear());
        u.setVacationDays(new ArrayList<>());

        return u;
    }

    @Override
    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .address(addressMapper.addressToAddressDto(user.getAddress()))
                .availableVacationDays(user.getAvailableVacationDays())
                .vacationDays(user.getVacationDays().stream().map(VacationDay::getDate).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<UserDto> userListToUserDtoList(List<User> users) {

        List<UserDto> usersDto = new ArrayList<>();

        users.forEach(user -> {
            usersDto.add(userToUserDto(user));
        });

        return usersDto;
    }

    @Override
    public UserViewDto userToUserViewDto(User user) {
        return UserViewDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber() == null ? "/" : user.getPhoneNumber())
                .role(translatingRole(user.getRole().getName()))
                .sector(sectorMapper.sectorTypeToString(user.getSector().getType()))
                .build();
    }

    @Override
    public List<UserViewDto> userListToWorkerOnMachineDtoList(List<User> users, Long machineId) {

        List<UserViewDto> workersDto = new ArrayList<>();

        users.forEach(user -> {
            UserViewDto dto = userToUserViewDto(user);
            WorkerOnMachine workerOnMachine = workerOnMachineRepository.findByWorkerIdAndMachineId(user.getId(), machineId);
            dto.setMainWorker(workerOnMachine.isMainWorker());
            workersDto.add(dto);
        });

        return workersDto;
    }

    @Override
    public List<UserViewDto> userListToUserViewDtoList(List<User> users) {

        List<UserViewDto> workersDto = new ArrayList<>();

        users.forEach(user -> {
            workersDto.add(userToUserViewDto(user));
        });

        return workersDto;
    }

    private int calculateVacationDaysForThisYear() {
        return (12 - LocalDate.now().getMonthValue()) * 20 / 12;
    }

    private String translatingRole(String roleName) {

        switch (roleName) {
            case "DIRECTOR":
                return "DIREKTOR";
            case "LEADER":
                return "RUKOVODILAC";
            case "WORKER":
                return "RADNIK";
            default:
                return "ADMIN";
        }
    }
}
