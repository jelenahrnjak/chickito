package com.ftn.Chickito.dto.user;

import com.ftn.Chickito.dto.AddressDto;
import com.ftn.Chickito.model.enums.GenderType;
import com.ftn.Chickito.model.enums.SectorType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private Long companyId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressDto address;
    private String role;
    private GenderType gender;
    private String sector;

}
