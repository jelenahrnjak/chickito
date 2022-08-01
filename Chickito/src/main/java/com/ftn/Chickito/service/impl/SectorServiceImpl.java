package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.model.Sector;
import com.ftn.Chickito.model.enums.SectorType;
import com.ftn.Chickito.repository.SectorRepository;
import com.ftn.Chickito.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;

    @Override
    public Sector findByCompanyAndType(Long companyId, SectorType type) {

        return this.sectorRepository.findByCompanyAndType(companyId,type);
    }
}
