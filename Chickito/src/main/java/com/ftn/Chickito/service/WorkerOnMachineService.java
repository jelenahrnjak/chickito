package com.ftn.Chickito.service;

import com.ftn.Chickito.dto.workerOnMachine.AddWorkerOnMachineDto;
import com.ftn.Chickito.dto.workerOnMachine.WorkerOnMachineDto;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.model.WorkerOnMachine;

import java.util.List;

public interface WorkerOnMachineService {

    List<User> findAllWorkersByMachine(Long machineId);
    List<Machine> findAllMachinesByWorker(String workerUsername);
    WorkerOnMachine addWorkerToMachine(AddWorkerOnMachineDto dto);

    void changeMainWorker(WorkerOnMachineDto workerOnMachineDto);

    List<User> findAllWorkersNotOnMachine(Long machineId);

    void delete(Long machineId, Long workerId);

    void deleteWorker(Long id);

    void deleteMachine(Long id);
}
