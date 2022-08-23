package com.ftn.Chickito.dto.order;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private String name;
    private String model;
    private String serialNumber;
    private String documentation;
    private Double price;
    private Integer quantity;

}