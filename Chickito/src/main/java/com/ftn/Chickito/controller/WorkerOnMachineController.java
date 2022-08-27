package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.user.UserDto;
import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.dto.workerOnMachine.AddWorkerOnMachineDto;
import com.ftn.Chickito.dto.user.UserViewDto;
import com.ftn.Chickito.dto.workerOnMachine.WorkerOnMachineDto;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.mapper.UserMapper;
import com.ftn.Chickito.service.WorkerOnMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/workerOnMachines")
@RequiredArgsConstructor
public class WorkerOnMachineController {

    private final WorkerOnMachineService workerOnMachineService;
    private final UserMapper userMapper;
    private final MachineMapper machineMapper;

    @GetMapping("/findAllWorkersByMachine/{machineId}")
    @PreAuthorize("hasAuthority('LEADER') || hasAuthority('DIRECTOR')")
    public ResponseEntity<List<UserViewDto>> findAllWorkersByMachine(@PathVariable Long machineId) {
        return new ResponseEntity<>(userMapper.userListToWorkerOnMachineDtoList(workerOnMachineService.findAllWorkersByMachine(machineId), machineId), HttpStatus.OK);
    }

    @GetMapping("/findAllMachinesByWorker/{workerId}")
    @PreAuthorize("hasAuthority('LEADER') || hasAuthority('DIRECTOR')")
    public ResponseEntity<List<MachineDto>> findAllMachinesByWorker(@PathVariable  Long workerId) {
        return new ResponseEntity<>(machineMapper.machineListToMachineDtoList(workerOnMachineService.findAllMachinesByWorker(workerId)), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity addWorkerToMachine(@RequestBody AddWorkerOnMachineDto workerOnMachineDto) {

        workerOnMachineService.addWorkerToMachine(workerOnMachineDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }    
    
    @PostMapping("/changeMainWorker")
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity changeMainWorker(@RequestBody WorkerOnMachineDto workerOnMachineDto) {

        workerOnMachineService.changeMainWorker(workerOnMachineDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findAllWorkersNotOnMachine/{machineId}")
    @PreAuthorize("hasAuthority('LEADER') || hasAuthority('DIRECTOR')")
    public ResponseEntity<List<UserDto>> findAllWorkersNotOnMachine(@PathVariable  Long machineId) {
        return new ResponseEntity<>(userMapper.userListToUserDtoList(workerOnMachineService.findAllWorkersNotOnMachine(machineId)), HttpStatus.OK);
    }


    @DeleteMapping("/{machineId}/{workerId}")
    @PreAuthorize("hasAuthority('LEADER')")
    public void delete(@PathVariable Long machineId, @PathVariable Long workerId) {
        this.workerOnMachineService.delete(machineId, workerId);
    }
}
