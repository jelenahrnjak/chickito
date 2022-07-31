package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.company.CompanyDto;
import com.ftn.Chickito.dto.company.CreateCompanyDto;
import com.ftn.Chickito.exception.ResourceConflictException;
import com.ftn.Chickito.mapper.CompanyMapper;
import com.ftn.Chickito.model.Company;
import com.ftn.Chickito.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper mapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CompanyDto loadById(@PathVariable Long id) {
        Company company = this.companyService.findById(id);
        return this.mapper.companyToCompanyDto(company);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CompanyDto> loadAll() {
        List<Company> companies = this.companyService.findAll();
        return this.mapper.companyListToCompanyDtoList(companies) ;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CreateCompanyDto request) {

        Company existName = this.companyService.findByName(request.getName());

        if (existName != null) {
            throw new ResourceConflictException(request.getId(), "Naziv preduzeća već postoji!");
        }

        Company existPib = this.companyService.findByPib(request.getPib());

        if (existPib != null) {
            throw new ResourceConflictException(request.getId(), "Postoji preduzeće sa istim pib-om!");
        }

        Company c = this.companyService.save(request);

        return new ResponseEntity<>(this.mapper.companyToCompanyDto(c), HttpStatus.CREATED);
    }

}
