package com.ftn.Chickito.service;

import com.ftn.Chickito.dto.companyDto.CreateCompanyDto;
import com.ftn.Chickito.model.Company;

import java.util.List;

public interface CompanyService {

    Company findById(Long id);
    Company findByName(String name);
    Company findByPib(String pib);
    List<Company> findAll ();
    Company save(CreateCompanyDto request);
}
