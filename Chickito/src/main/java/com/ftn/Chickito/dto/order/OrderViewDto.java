package com.ftn.Chickito.dto.order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderViewDto {

    private Long id;
    private String author;
    private String reviewer;
    private Double price;
    private String creationDate;
    private Boolean approved;
    private List<OrderItemDto> orderItems;

}
