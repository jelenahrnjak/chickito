package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.dto.order.OrderViewDto;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.Order;
import com.ftn.Chickito.service.MachineService;
import com.ftn.Chickito.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
}
