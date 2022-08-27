package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.company.CreateCompanyDto;
import com.ftn.Chickito.mapper.CompanyMapper;
import com.ftn.Chickito.model.Building;
import com.ftn.Chickito.model.Company;
import com.ftn.Chickito.model.Sector;
import com.ftn.Chickito.model.enums.SectorType;
import com.ftn.Chickito.repository.BuildingRepository;
import com.ftn.Chickito.repository.CompanyRepository;
import com.ftn.Chickito.repository.SectorRepository;
import com.ftn.Chickito.service.BuildingService;
import com.ftn.Chickito.service.CompanyService;
import com.ftn.Chickito.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final BuildingService buildingService;
    private final UserService userService;
    private final SectorRepository sectorRepository;
    private final CompanyMapper mapper;

    @Override
    public Company findById(Long id) {
        return this.companyRepository.findById(id).orElseGet(null);
    }

    @Override
    public Company findByName(String name) {
        return this.companyRepository.findByName(name);
    }

    @Override
    public Company findByPib(String pib) {
        return this.companyRepository.findByPib(pib);
    }

    @Override
    public List<Company> findAll() {
        return this.companyRepository.findAllNotDeleted();
    }

    @Override
    public Company save(CreateCompanyDto request) {

        Company toSave = this.mapper.createCompanyDtoToCompany(request);
        Company newCompany = this.companyRepository.save(toSave);

        for(SectorType type : SectorType.values()){
            Sector sector = new Sector();
            sector.setCompany(newCompany);
            sector.setType(type);
            this.sectorRepository.save(sector);
        }

        return newCompany;
    }

    @Override
    public void delete(Long id) {
        Company toDelete = this.companyRepository.findById(id).orElseGet(null);
        if(toDelete == null){
            return;
        }

        this.buildingService.deleteCompanyBuildings(id);
        this.userService.deleteCompanyUsers(id);
        toDelete.setDeleted(true);
        this.companyRepository.save(toDelete);

    }
}
