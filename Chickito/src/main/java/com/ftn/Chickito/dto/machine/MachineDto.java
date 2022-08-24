package com.ftn.Chickito.dto.machine;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MachineDto {

    private Long id;
    private String name;
    private String model;
    private String serialNumber;
    private Long sectorId;
    private String sector;
    private String documentation;
    private Double price;
    private Integer quantity;
}
