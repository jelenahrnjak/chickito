package com.ftn.Chickito.dto.company;

import com.ftn.Chickito.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBuildingDto {

    private Long companyId;
    private AddressDto address;
    private boolean headOffice;
}
