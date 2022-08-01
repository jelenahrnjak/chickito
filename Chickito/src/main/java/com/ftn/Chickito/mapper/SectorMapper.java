package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.SectorDto;
import com.ftn.Chickito.model.Sector;

public interface SectorMapper {

    SectorDto sectorToSectorDto(Sector s);
}
