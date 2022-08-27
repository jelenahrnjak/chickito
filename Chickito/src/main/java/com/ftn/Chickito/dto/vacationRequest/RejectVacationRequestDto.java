package com.ftn.Chickito.dto.vacationRequest;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RejectVacationRequestDto {

    private String reasonForRejection;
}
