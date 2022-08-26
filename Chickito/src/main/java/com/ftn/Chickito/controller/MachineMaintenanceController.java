package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.machineMaintenance.CreateMachineMaintenanceDto;
import com.ftn.Chickito.dto.machineMaintenance.MachineMaintenanceViewDto;
import com.ftn.Chickito.mapper.MachineMaintenanceMapper;
import com.ftn.Chickito.service.MachineMaintenanceService;
import com.ftn.Chickito.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/machineMaintenances")
@RequiredArgsConstructor
public class MachineMaintenanceController {

    private final MachineMaintenanceService machineMaintenanceService;
    private final TokenUtils tokenUtils;
    private final MachineMaintenanceMapper mapper;
    private static final String WHITESPACE = " ";

    @PostMapping
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity<MachineMaintenanceViewDto> createMachineMaintenance(@RequestHeader("Authorization") String jwtToken, @RequestBody CreateMachineMaintenanceDto createDto) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(mapper.maintenanceToMaintenanceDto(machineMaintenanceService.createMachineMaintenance(username, createDto)), HttpStatus.CREATED);
    }

    @GetMapping("/generateMaintenancePdf/{year}")
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity generateMaintenancePdf(@RequestHeader("Authorization") String jwtToken, @PathVariable Integer year) throws JRException, IOException, MessagingException {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);
        machineMaintenanceService.generateMaintenancePdfLeader(username, year);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/generateMaintenancePdf/{year}/{sector}")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public ResponseEntity generateMaintenancePdfDirector(@RequestHeader("Authorization") String jwtToken, @PathVariable Integer year, @PathVariable String sector) throws JRException, IOException, MessagingException {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);
        machineMaintenanceService.generateMaintenancePdfDirector(username, year, sector);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/findAllByAuthor")
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity<List<MachineMaintenanceViewDto>> findAllByAuthor(@RequestHeader("Authorization") String jwtToken) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(mapper.maintenanceListToMaintenanceDtoList(machineMaintenanceService.findAllByAuthor(username)), HttpStatus.OK);
    }

    @GetMapping("/findAllByDirector")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public ResponseEntity<List<MachineMaintenanceViewDto>> findAllByDirector(@RequestHeader("Authorization") String jwtToken) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(mapper.maintenanceListToMaintenanceDtoList(machineMaintenanceService.findAllByDirector(username)), HttpStatus.OK);
    }

}
