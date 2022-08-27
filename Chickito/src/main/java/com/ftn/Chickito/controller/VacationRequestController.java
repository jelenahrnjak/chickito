package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.vacationRequest.RejectVacationRequestDto;
import com.ftn.Chickito.dto.vacationRequest.VacationRequestDto;
import com.ftn.Chickito.service.VacationRequestService;
import com.ftn.Chickito.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/vacation-requests")
@RequiredArgsConstructor
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;
    private final TokenUtils tokenUtils;

    private static final String WHITESPACE = " ";

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','DIRECTOR','LEADER','WORKER')")
    public boolean createVacationRequest(@RequestHeader("Authorization") String jwtToken, @RequestBody VacationRequestDto vacationRequestDto) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return vacationRequestService.createVacationRequest(username, vacationRequestDto.getStartDate(), vacationRequestDto.getEndDate());
    }

    @PostMapping(value = "/{id}/approve")
    @PreAuthorize("hasAnyAuthority('DIRECTOR')")
    public boolean approveVacationRequest(@RequestHeader("Authorization") String jwtToken, @PathVariable Long id) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return vacationRequestService.approveVacationRequest(username, id);
    }

    @PostMapping(value = "/{id}/reject")
    @PreAuthorize("hasAnyAuthority('DIRECTOR')")
    public boolean rejectVacationRequest(@RequestHeader("Authorization") String jwtToken, @PathVariable Long id, @RequestBody RejectVacationRequestDto rejectVacationRequestDto) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return vacationRequestService.rejectVacationRequest(username, id, rejectVacationRequestDto.getReasonForRejection());
    }
}
