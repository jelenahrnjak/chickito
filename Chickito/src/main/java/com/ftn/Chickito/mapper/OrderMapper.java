package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.order.OrderDto;
import com.ftn.Chickito.dto.order.OrderItemDto;
import com.ftn.Chickito.dto.order.OrderViewDto;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.Order;

public interface OrderMapper {

    OrderDto orderToOrderDto(Order order);
    OrderViewDto orderToOrderViewDto(Order order);
    OrderItemDto machineToOrderItemDto(Machine machine);
}
