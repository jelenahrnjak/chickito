package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.company.CreateCompanyDto;
import com.ftn.Chickito.mapper.CompanyMapper;
import com.ftn.Chickito.model.Building;
import com.ftn.Chickito.model.Company;
import com.ftn.Chickito.repository.BuildingRepository;
import com.ftn.Chickito.repository.CompanyRepository;
import com.ftn.Chickito.service.BuildingService;
import com.ftn.Chickito.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final BuildingService buildingService;
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

        Company newCompany = this.mapper.createCompanyDtoToCompany(request);
        return this.companyRepository.save(newCompany);
    }

    @Override
    public void delete(Long id) {
        Company toDelete = this.companyRepository.findById(id).orElseGet(null);
        if(toDelete == null){
            return;
        }

        this.buildingService.deleteCompanyBuildings(id);
        toDelete.setDeleted(true);
        this.companyRepository.save(toDelete);

    }
}
