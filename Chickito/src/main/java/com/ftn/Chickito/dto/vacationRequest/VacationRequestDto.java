package com.ftn.Chickito.dto.vacationRequest;

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
