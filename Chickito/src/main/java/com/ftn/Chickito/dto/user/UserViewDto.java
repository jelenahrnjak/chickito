package com.ftn.Chickito.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserViewDto {

    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String sector;
    private String role;
    private boolean mainWorker;
    private int vacationDaysPerYear;

}
