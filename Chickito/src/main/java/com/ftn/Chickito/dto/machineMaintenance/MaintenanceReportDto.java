package com.ftn.Chickito.dto.machineMaintenance;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaintenanceReportDto {

    private String author;
    private String companyName;
    private String headOffice;
    private String sector;
    private String title;
    private Integer year;
}
