package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.building.BuildingDto;
import com.ftn.Chickito.dto.company.CompanyDto;
import com.ftn.Chickito.dto.company.CreateBuildingDto;
import com.ftn.Chickito.dto.company.CreateCompanyDto;
import com.ftn.Chickito.model.Building;
import com.ftn.Chickito.model.Company;

import java.util.List;

public interface BuildingMapper {

    Building createBuildingDtoToBuilding(CreateBuildingDto dto);
    BuildingDto buildingToBuildingDto(Building b);
    List<BuildingDto> buildingListToBuildingDtoList(List<Building> buildings);
}
