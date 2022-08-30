package com.ftn.Chickito.dto.vacationRequest;

import com.ftn.Chickito.dto.user.UserDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestViewDto {

    private Long id;
    private String requestExpirationDate;
    private String startDate;
    private String endDate;
    private UserDto user;
    private Boolean approved;
    private String reasonForRejection;
}

