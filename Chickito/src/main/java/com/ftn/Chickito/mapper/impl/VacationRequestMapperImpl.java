package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.vacationRequest.VacationRequestViewDto;
import com.ftn.Chickito.mapper.UserMapper;
import com.ftn.Chickito.mapper.VacationRequestMapper;
import com.ftn.Chickito.model.VacationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VacationRequestMapperImpl implements VacationRequestMapper {

    private final UserMapper userMapper;

    @Override
    public VacationRequestViewDto vacationRequestToVacationRequestDto(VacationRequest vacationRequest) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return VacationRequestViewDto.builder()
                .id(vacationRequest.getId())
                .user(userMapper.userToUserDto(vacationRequest.getUser()))
                .approved(vacationRequest.getApproved())
                .startDate(vacationRequest.getDates().get(0).getDate().format(formatter))
                .endDate(vacationRequest.getDates().get(vacationRequest.getDates().size() - 1).getDate().format(formatter))
                .requestExpirationDate(vacationRequest.getRequestExpirationDate().format(formatter))
                .reasonForRejection(vacationRequest.getReasonForRejection() == null ? "" : vacationRequest.getReasonForRejection())
                .build();
    }

    @Override
    public List<VacationRequestViewDto> vacationRequestListToDtoList(List<VacationRequest> vacationRequests) {

        List<VacationRequestViewDto> ret = new ArrayList<>();

        vacationRequests.forEach(vacationRequest -> {
            ret.add(vacationRequestToVacationRequestDto(vacationRequest));
        });

        return ret;
    }
}
