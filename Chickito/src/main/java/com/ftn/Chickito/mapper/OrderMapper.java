package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.order.OrderDto;
import com.ftn.Chickito.dto.order.OrderItemDto;
import com.ftn.Chickito.dto.order.OrderViewDto;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.Order;

import java.util.List;

public interface OrderMapper {

    OrderDto orderToOrderDto(Order order);
    OrderViewDto orderToOrderViewDto(Order order);
    List<OrderViewDto> orderListToOrderViewDtoList(List<Order> orders);
    OrderItemDto machineToOrderItemDto(Machine machine);
}
