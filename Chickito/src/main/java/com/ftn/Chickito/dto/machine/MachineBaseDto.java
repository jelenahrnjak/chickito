package com.ftn.Chickito.dto.machine;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MachineBaseDto {

    private Long id;
    private String name;
    private String model;
    private Integer quantity;
}
