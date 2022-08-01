package com.ftn.Chickito.service;

import com.ftn.Chickito.dto.company.CreateBuildingDto;
import com.ftn.Chickito.model.Building;

import java.util.List;

public interface BuildingService {

    List<Building> findAllByCompany (Long companyId);
    Building save(CreateBuildingDto request);
    Building findHeadOfficeOfCompany(Long companyId);
    void delete(Long id);
    void deleteCompanyBuildings(Long companyId);
    void changeHeadOffice(Long id);
}
