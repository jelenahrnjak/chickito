package com.ftn.Chickito.dto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
