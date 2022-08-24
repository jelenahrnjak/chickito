package com.ftn.Chickito.dto.machineMaintenance;

import com.ftn.Chickito.dto.machine.MachineDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MachineMaintenanceItemDto {

    private Long id;
    private MachineDto machine;
    private String plan;
}
