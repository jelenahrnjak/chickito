package com.ftn.Chickito.dto.machineMaintenance;

import com.ftn.Chickito.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MachineMaintenanceViewDto {

    private Long id;
    private String author;
    private List<MachineMaintenanceItemDto> items;
    private String creationDate;
    private String sector;
    private String startDate;
    private String endDate;
}
