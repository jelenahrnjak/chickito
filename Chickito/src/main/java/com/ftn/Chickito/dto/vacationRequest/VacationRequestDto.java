package com.ftn.Chickito.dto.vacationRequest;

import com.ftn.Chickito.dto.user.UserDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestDto {

    private LocalDate startDate;
    private LocalDate endDate;
}
