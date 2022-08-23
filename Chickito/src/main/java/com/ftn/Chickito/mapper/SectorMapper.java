package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.SectorDto;
import com.ftn.Chickito.model.Sector;
import com.ftn.Chickito.model.enums.SectorType;

public interface SectorMapper {

    SectorDto sectorToSectorDto(Sector s);
    String sectorTypeToString(SectorType type);
}
