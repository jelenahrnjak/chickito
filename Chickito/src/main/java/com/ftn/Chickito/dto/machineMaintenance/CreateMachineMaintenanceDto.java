package com.ftn.Chickito.dto.machineMaintenance;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMachineMaintenanceDto {

    private List<MachineMaintenanceItemDto> items;
    private Integer year;
}
