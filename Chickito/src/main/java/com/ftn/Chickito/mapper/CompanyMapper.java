package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.companyDto.CompanyDto;
import com.ftn.Chickito.dto.companyDto.CreateCompanyDto;
import com.ftn.Chickito.model.Company;

import java.util.List;

public interface CompanyMapper {

    CompanyDto companyToCompanyDto(Company company);
    Company createCompanyDtoToCompany(CreateCompanyDto dto);
    List<CompanyDto> companyListToCompanyDtoList(List<Company> companySet);


}
