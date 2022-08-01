package com.ftn.Chickito.service;

import com.ftn.Chickito.model.Sector;
import com.ftn.Chickito.model.enums.SectorType;

public interface SectorService {

    Sector findByCompanyAndType(Long companyId, SectorType type);
}
