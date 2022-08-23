package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.SectorDto;
import com.ftn.Chickito.mapper.SectorMapper;
import com.ftn.Chickito.model.Sector;
import com.ftn.Chickito.model.enums.SectorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SectorMapperImpl implements SectorMapper {
    @Override
    public SectorDto sectorToSectorDto(Sector s) {
        SectorDto dto = new SectorDto();
        dto.setId(s.getId());
        dto.setCompanyId(s.getCompany().getId());
        if(s.getLeader() != null){
            dto.setLeader(s.getLeader().getFirstName() + " " + s.getLeader().getLastName());
        }
        dto.setType(s.getType().ordinal());

        return dto;
    }

    @Override
    public String sectorTypeToString(SectorType type) {

        switch (type){
            case PRERADA:
                return "Prerada";
            case KLANICA_I_PAKERAJ:
                return "Klanica i pakeraj";
            case KONTROLA_KVALITETA:
                return "Kontrola kvaliteta";
            default:
                return "Održavanje i energetika";
        }
    }


}
