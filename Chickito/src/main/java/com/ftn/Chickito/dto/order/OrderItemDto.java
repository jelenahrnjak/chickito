package com.ftn.Chickito.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private String name;
    private String model;
    private String serialNumber;
    private String documentation;
    private Double price;
}
