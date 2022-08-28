package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.workerOnMachine.AddWorkerOnMachineDto;
import com.ftn.Chickito.dto.workerOnMachine.WorkerOnMachineDto;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.model.WorkerOnMachine;
import com.ftn.Chickito.repository.MachineRepository;
import com.ftn.Chickito.repository.UserRepository;
import com.ftn.Chickito.repository.WorkerOnMachineRepository;
import com.ftn.Chickito.service.WorkerOnMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerOnMachineServiceImpl implements WorkerOnMachineService {

    private final WorkerOnMachineRepository workerOnMachineRepository;
    private final MachineRepository machineRepository;
    private final UserRepository userRepository;
    private final static String WORKER_ROLE = "WORKER";

    @Override
    public List<User> findAllWorkersByMachine(Long machineId) {
        return this.workerOnMachineRepository.findAllWorkersByMachine(machineId);
    }

    @Override
    public List<Machine> findAllMachinesByWorker(String workerUsername) {
        return this.workerOnMachineRepository.findAllMachinesByWorker(workerUsername);
    }

    @Override
    public WorkerOnMachine addWorkerToMachine(AddWorkerOnMachineDto workerOnMachineDto) {

        Machine machine = machineRepository.findById(workerOnMachineDto.getMachineId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Machine with id = %s doesn't exist.", workerOnMachineDto.getMachineId())));

        User worker = getWorker(workerOnMachineDto.getUserId());

        WorkerOnMachine workerOnMachine = WorkerOnMachine.builder()
                .machine(machine)
                .worker(worker)
                .mainWorker(workerOnMachineDto.isMainWorker())
                .build();

        return workerOnMachineRepository.save(workerOnMachine);
    }

    @Override
    public void changeMainWorker(WorkerOnMachineDto workerOnMachineDto) {

        WorkerOnMachine workerOnMachine = workerOnMachineRepository.findByWorkerIdAndMachineId(workerOnMachineDto.getUserId(), workerOnMachineDto.getMachineId());


        boolean mainWorker = workerOnMachine.isMainWorker();
        workerOnMachine.setMainWorker(!mainWorker);
        workerOnMachineRepository.save(workerOnMachine);

    }

    @Override
    public List<User> findAllWorkersNotOnMachine(Long machineId) {

        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Machine with id = %s doesn't exist.", machineId)));

        List<User> workersInSector = userRepository.findAllBySectorAndRole(machine.getSector().getId(), WORKER_ROLE);

        List<User> ret = new ArrayList<>();
        workersInSector.forEach(user -> {
            if(workerOnMachineRepository.findByWorkerIdAndMachineId(user.getId(),machineId) == null){
                ret.add(user);
            }
        });

        return ret;
    }

    @Override
    public void delete(Long machineId, Long workerId) {
        this.workerOnMachineRepository.delete(workerOnMachineRepository.findByWorkerIdAndMachineId(workerId, machineId));
    }

    @Override
    public void deleteWorker(Long workerId) {

        User worker = getWorker(workerId);

        findAllMachinesByWorker(worker.getUsername()).forEach(machine -> {
            delete(machine.getId(), workerId);
        });
    }

    private User getWorker(Long id) {
        return userRepository.findByIdAndRoleName(id, WORKER_ROLE)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Worker with id = %s doesn't exist.", id)));
    }
}
