package com.ftn.Chickito.dto.workerOnMachine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerOnMachineDto {
    Long machineId;
    Long userId;
    boolean mainWorker;
}
