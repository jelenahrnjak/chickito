package com.ftn.Chickito.dto.building;

import com.ftn.Chickito.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDto {

    private Long id;
    private Long companyId;
    private String companyName;
    private AddressDto address;
    private boolean headOffice;
}
