package com.ftn.Chickito.dto.machine;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SparePartDto {

    private Long id;
    private String stockNumber;
    private String name;
    private Integer quantity;
}
