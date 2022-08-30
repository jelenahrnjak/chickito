package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.order.OrderViewDto;
import com.ftn.Chickito.dto.vacationRequest.RejectVacationRequestDto;
import com.ftn.Chickito.dto.vacationRequest.VacationRequestDto;
import com.ftn.Chickito.dto.vacationRequest.VacationRequestViewDto;
import com.ftn.Chickito.mapper.VacationRequestMapper;
import com.ftn.Chickito.service.VacationRequestService;
import com.ftn.Chickito.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/vacation-requests")
@RequiredArgsConstructor
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;
    private final VacationRequestMapper mapper;
    private final TokenUtils tokenUtils;

    private static final String WHITESPACE = " ";

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public boolean createVacationRequest(@RequestHeader("Authorization") String jwtToken, @RequestBody VacationRequestDto vacationRequestDto) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return vacationRequestService.createVacationRequest(username, vacationRequestDto.getStartDate(), vacationRequestDto.getEndDate());
    }

    @PostMapping(value = "/{id}/approve")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public boolean approveVacationRequest(@RequestHeader("Authorization") String jwtToken, @PathVariable Long id) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return vacationRequestService.approveVacationRequest(username, id);
    }

    @PostMapping(value = "/{id}/reject")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public boolean rejectVacationRequest(@RequestHeader("Authorization") String jwtToken, @PathVariable Long id, @RequestBody RejectVacationRequestDto rejectVacationRequestDto) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return vacationRequestService.rejectVacationRequest(username, id, rejectVacationRequestDto.getReasonForRejection());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public ResponseEntity<List<VacationRequestViewDto>> findAllByDirector(@RequestHeader("Authorization") String jwtToken) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(mapper.vacationRequestListToDtoList(vacationRequestService.findAllByDirector(username)), HttpStatus.OK);
    }
    @GetMapping("/findAllByUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<VacationRequestViewDto>> findAllByUser(@RequestHeader("Authorization") String jwtToken) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(mapper.vacationRequestListToDtoList(vacationRequestService.findAllByUser(username)), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void delete(@RequestHeader("Authorization") String jwtToken, @PathVariable Long id) throws Exception {


        String currentUser = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);
        this.vacationRequestService.delete(currentUser, id);
    }

}
