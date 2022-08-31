package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.machine.DocumentationDto;
import com.ftn.Chickito.dto.machine.MachineBaseDto;
import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.service.MachineService;
import com.ftn.Chickito.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/machines")
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;
    private final TokenUtils tokenUtils;
    private final MachineMapper mapper;
    private static final String WHITESPACE = " ";

    @GetMapping("/findAllByLeader")
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity<List<MachineDto>> findAllByLeader(@RequestHeader("Authorization") String jwtToken) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(mapper.machineListToMachineDtoList(machineService.findAllByLeader(username)), HttpStatus.OK);
    }

    @GetMapping("/findAllByDirector")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public ResponseEntity<List<MachineDto>> findAllByDirector(@RequestHeader("Authorization") String jwtToken) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(mapper.machineListToMachineDtoList(machineService.findAllByDirector(username)), HttpStatus.OK);
    }

    @PostMapping("/addDocumentation/{machineId}")
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity<MachineDto> addDocumentation(@PathVariable Long machineId, @RequestBody DocumentationDto documentationDto) {

        return new ResponseEntity<>(mapper.machineToMachineDto(machineService.addDocumentation(machineId, documentationDto)), HttpStatus.CREATED);
    }

    @PostMapping("/{machineId}")
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity<MachineDto> editMachine(@PathVariable Long machineId, @RequestBody MachineBaseDto editDto) {

        return new ResponseEntity<>(mapper.machineToMachineDto(machineService.editMachine(machineId, editDto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('DIRECTOR')")
    public void delete(@PathVariable Long id) {
        this.machineService.delete(id);
    }
}
