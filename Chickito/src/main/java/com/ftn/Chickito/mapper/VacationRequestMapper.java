package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.vacationRequest.VacationRequestViewDto;
import com.ftn.Chickito.model.VacationRequest;

import java.util.List;

public interface VacationRequestMapper {

    VacationRequestViewDto vacationRequestToVacationRequestDto(VacationRequest vacationRequest);
    List<VacationRequestViewDto> vacationRequestListToDtoList(List<VacationRequest> vacationRequests);
}
