package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.building.BuildingDto;
import com.ftn.Chickito.dto.company.CreateBuildingDto;
import com.ftn.Chickito.mapper.BuildingMapper;
import com.ftn.Chickito.model.Building;
import com.ftn.Chickito.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;
    private final BuildingMapper mapper;


    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BuildingDto> addBuilding(@RequestBody CreateBuildingDto request) {

        Building b = this.buildingService.save(request);

        return new ResponseEntity<>(this.mapper.buildingToBuildingDto(b), HttpStatus.CREATED);
    }
}
