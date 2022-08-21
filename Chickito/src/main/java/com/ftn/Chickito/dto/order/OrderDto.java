package com.ftn.Chickito.dto.order;

import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.User;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private User author;
    private User reviewer;
    private Double price;
    private Set<Machine> machines;
}
