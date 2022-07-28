package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.companyDto.CompanyDto;
import com.ftn.Chickito.dto.companyDto.CreateCompanyDto;
import com.ftn.Chickito.mapper.CompanyMapper;
import com.ftn.Chickito.model.Company;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDto companyToCompanyDto(Company company) {

        CompanyDto dto = new CompanyDto();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setPib(company.getPib());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDateTime = company.getEstablishmentDate().format(formatter);
        dto.setEstablishmentDate(formattedDateTime);

        return dto;
    }

    @Override
    public Company createCompanyDtoToCompany(CreateCompanyDto dto) {

        Company company = new Company();
        company.setName(dto.getName());
        company.setPib(dto.getPib());
        return company;
    }

    @Override
    public List<CompanyDto> companyListToCompanyDtoList(List<Company> companySet) {

        List<CompanyDto> dtoSet = new ArrayList<>();
        for ( Company company : companySet ) {
            dtoSet.add( companyToCompanyDto( company ) );
        }

        return dtoSet;
    }
}
