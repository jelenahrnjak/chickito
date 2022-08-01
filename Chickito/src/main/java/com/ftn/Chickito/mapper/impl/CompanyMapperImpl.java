package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.company.CompanyDto;
import com.ftn.Chickito.dto.company.CreateCompanyDto;
import com.ftn.Chickito.mapper.AddressMapper;
import com.ftn.Chickito.mapper.CompanyMapper;
import com.ftn.Chickito.model.Address;
import com.ftn.Chickito.model.Building;
import com.ftn.Chickito.model.Company;
import com.ftn.Chickito.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyMapperImpl implements CompanyMapper {

    private final BuildingRepository buildingRepository;
    private final AddressMapper addressMapper;

    @Override
    public CompanyDto companyToCompanyDto(Company company) {

        CompanyDto dto = new CompanyDto();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setPib(company.getPib());

        if(company.getEstablishmentDate() != null){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDateTime = company.getEstablishmentDate().format(formatter);
        dto.setEstablishmentDate(formattedDateTime);}

        Building headOffice = buildingRepository.findHeadOfficeOfCompany(company.getId());
        if(headOffice!=null){
            dto.setHeadOfficeId(headOffice.getId());
            dto.setHeadOfficeAddress(this.addressMapper.getAddressString(headOffice.getAddress()));
        }
        return dto;
    }

    @Override
    public Company createCompanyDtoToCompany(CreateCompanyDto dto) {

        Company company = new Company();
        company.setName(dto.getName());
        company.setPib(dto.getPib());
        company.setEstablishmentDate(LocalDateTime.now());
        company.setDeleted(false);
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
