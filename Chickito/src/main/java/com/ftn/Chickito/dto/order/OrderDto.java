package com.ftn.Chickito.dto.order;

import com.ftn.Chickito.dto.user.UserDto;
import com.ftn.Chickito.dto.machine.MachineDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private UserDto author;
    private UserDto reviewer;
    private Double price;
    private Set<MachineDto> machines;
}
