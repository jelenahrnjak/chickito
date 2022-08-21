package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.order.OrderDto;
import com.ftn.Chickito.model.Order;

public interface OrderMapper {

    OrderDto orderToOrderDto(Order order);
}
