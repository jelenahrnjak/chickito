package com.ftn.Chickito.service;

import com.ftn.Chickito.dto.order.CreateOrderDto;
import com.ftn.Chickito.model.Order;

public interface OrderService {

    Order createOrder(String authorUsername, CreateOrderDto createOrderDto);

    boolean approveOrder(String reviewerUsername, Long id);

    boolean declineOrder(String reviewerUsername, Long id);
}
