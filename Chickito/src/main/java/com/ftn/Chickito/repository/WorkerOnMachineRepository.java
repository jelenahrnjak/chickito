package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.model.WorkerOnMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkerOnMachineRepository extends JpaRepository<WorkerOnMachine, Long> {

    @Query("select u.worker from  WorkerOnMachine u where u.machine.id = :machineId and u.worker.deleted = false")
    List<User> findAllWorkersByMachine(Long machineId);

    @Query("select u.machine from  WorkerOnMachine u where u.worker.username = :workerUsername and u.machine.active = true")
    List<Machine> findAllMachinesByWorker(String workerUsername);


    @Query("select u from  WorkerOnMachine u where u.worker.id = :workerId and u.machine.id = :machineId and u.machine.active = true and u.worker.deleted = false")
    WorkerOnMachine findByWorkerIdAndMachineId(Long workerId, Long machineId);
}
