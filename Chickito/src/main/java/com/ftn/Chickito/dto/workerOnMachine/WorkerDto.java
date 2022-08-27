package com.ftn.Chickito.dto.workerOnMachine;

import com.ftn.Chickito.dto.AddressDto;
import com.ftn.Chickito.model.enums.GenderType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {

    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private boolean mainWorker;

}
