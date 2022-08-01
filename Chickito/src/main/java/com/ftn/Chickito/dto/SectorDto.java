package com.ftn.Chickito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SectorDto {

    private Long id;
    private int type;
    private String leader;
    private Long companyId;

}
