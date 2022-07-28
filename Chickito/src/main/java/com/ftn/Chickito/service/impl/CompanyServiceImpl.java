package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.companyDto.CreateCompanyDto;
import com.ftn.Chickito.model.Company;
import com.ftn.Chickito.repository.CompanyRepository;
import com.ftn.Chickito.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

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
        return this.companyRepository.findAll();
    }

    @Override
    public Company save(CreateCompanyDto request) {

        Company newCompany = new Company();
        newCompany.setName(request.getName());
        newCompany.setPib(request.getPib());
        newCompany.setEstablishmentDate(LocalDateTime.now());

        return this.companyRepository.save(newCompany);
    }
}
