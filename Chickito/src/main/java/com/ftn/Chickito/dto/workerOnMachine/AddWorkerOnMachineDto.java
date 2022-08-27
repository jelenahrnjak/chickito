package com.ftn.Chickito.dto.workerOnMachine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddWorkerOnMachineDto {
    Long machineId;
    Long UserId;
    boolean mainWorker;
}
