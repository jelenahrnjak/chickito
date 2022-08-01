package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.SectorDto;
import com.ftn.Chickito.mapper.SectorMapper;
import com.ftn.Chickito.model.Sector;
import com.ftn.Chickito.model.enums.SectorType;
import com.ftn.Chickito.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sectors")
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;
    private final SectorMapper sectorMapper;

    @GetMapping("/findByCompanyAndType/{companyId}/{sectorType}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SectorDto findByCompanyAndType(@PathVariable Long companyId, @PathVariable int sectorType) {
        Sector sector = this.sectorService.findByCompanyAndType(companyId, SectorType.values()[sectorType]);
        return this.sectorMapper.sectorToSectorDto(sector);
    }
}
