package com.ftn.Chickito.dto.machine;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentationDto {

    private Long id;
    private String workInstructions;
    private String washingInstructions;
    private String maintenanceInstructions;
    private Set<SparePartDto> spareParts;
}
