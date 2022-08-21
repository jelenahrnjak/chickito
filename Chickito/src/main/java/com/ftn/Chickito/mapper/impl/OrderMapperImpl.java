package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.order.OrderDto;
import com.ftn.Chickito.mapper.OrderMapper;
import com.ftn.Chickito.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto orderToOrderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .machines(order.getMachines())
                .price(order.getPrice())
                .author(order.getAuthor())
                .reviewer(order.getReviewer())
                .build();
    }
}
