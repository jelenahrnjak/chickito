package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.building.BuildingDto;
import com.ftn.Chickito.dto.company.CreateBuildingDto;
import com.ftn.Chickito.mapper.AddressMapper;
import com.ftn.Chickito.mapper.BuildingMapper;
import com.ftn.Chickito.model.Address;
import com.ftn.Chickito.model.Building;
import com.ftn.Chickito.model.City;
import com.ftn.Chickito.model.Country;
import com.ftn.Chickito.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildingMapperImpl implements BuildingMapper {

    private final CompanyRepository companyRepository;
    private final AddressMapper addressMapper;

    @Override
    public Building createBuildingDtoToBuilding(CreateBuildingDto dto) {
        Building newBuilding = new Building();
        newBuilding.setAddress(this.addressMapper.addressDtoToAddress(dto.getAddress()));
        newBuilding.setCompany(this.companyRepository.findById(dto.getCompanyId()).orElseGet(null));
        newBuilding.setHeadOffice(dto.isHeadOffice());

        return newBuilding;
    }

    @Override
    public BuildingDto buildingToBuildingDto(Building b) {
        BuildingDto dto = new BuildingDto();
        dto.setAddress(this.addressMapper.addressToAddressDto(b.getAddress()));
        dto.setCompanyId(b.getCompany().getId());
        dto.setCompanyName(b.getCompany().getName());
        dto.setHeadOffice(dto.isHeadOffice());

        return dto;
    }
}
