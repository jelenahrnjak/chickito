package com.ftn.Chickito.dto.company;

import com.ftn.Chickito.dto.SectorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String name;
    private String pib;
    private String establishmentDate;
    private Long headOfficeId;
    private String headOfficeAddress;
    private List<SectorDto> sectors;
}
