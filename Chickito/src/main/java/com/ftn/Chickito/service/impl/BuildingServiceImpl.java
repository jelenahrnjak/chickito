package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.company.CreateBuildingDto;
import com.ftn.Chickito.mapper.BuildingMapper;
import com.ftn.Chickito.model.Building;
import com.ftn.Chickito.model.City;
import com.ftn.Chickito.model.Country;
import com.ftn.Chickito.repository.BuildingRepository;
import com.ftn.Chickito.repository.CityRepository;
import com.ftn.Chickito.repository.CountryRepository;
import com.ftn.Chickito.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final BuildingMapper mapper;

    @Override
    public List<Building> findAllByCompany(Long companyId) {
        return this.buildingRepository.findAllByCompany(companyId);
    }

    @Override
    public Building save(CreateBuildingDto request) {

        Building newBuilding = this.mapper.createBuildingDtoToBuilding(request);

        if(request.isHeadOffice()){
            removeOldHeadOffice(request.getCompanyId());
        }

        City city = this.cityRepository.findByName(request.getAddress().getCity().getName());
        Country country = this.countryRepository.findByName(request.getAddress().getCity().getCountry());

        if(country != null){
            newBuilding.getAddress().getCity().setCountry(country);
        }else{
            Country c = this.countryRepository.save(newBuilding.getAddress().getCity().getCountry());
            newBuilding.getAddress().getCity().setCountry(c);
        }

        if(city != null){
            newBuilding.getAddress().setCity(city);
        }else{
            this.cityRepository.save(newBuilding.getAddress().getCity());
        }

        return  this.buildingRepository.save(newBuilding);
    }

    @Override
    public Building findHeadOfficeOfCompany(Long companyId) {
        return this.buildingRepository.findHeadOfficeOfCompany(companyId);
    }

    @Override
    public void delete(Long id) {
        Building toDelete = this.buildingRepository.findById(id).orElseGet(null);
        if(toDelete == null){
            return;
        }
        toDelete.setDeleted(true);
        toDelete.setHeadOffice(false); //TODO : ??
        this.buildingRepository.save(toDelete);
    }

    @Override
    public void deleteCompanyBuildings(Long companyId) {
        for(Building b : findAllByCompany(companyId)){
            delete(b.getId());
        }
    }

    private void removeOldHeadOffice(Long companyId){
        Building previousHeadOffice = findHeadOfficeOfCompany(companyId);
        if(previousHeadOffice != null){
            previousHeadOffice.setHeadOffice(false);
            this.buildingRepository.save(previousHeadOffice);
        }
    }

    @Override
    public void changeHeadOffice(Long id) {
        Building toChange = this.buildingRepository.findById(id).orElseGet(null);
        if(toChange == null){
            return;
        }

        if(!toChange.isHeadOffice()){
           removeOldHeadOffice(toChange.getCompany().getId());
        }

        toChange.setHeadOffice(!toChange.isHeadOffice());
        this.buildingRepository.save(toChange);
    }
}
