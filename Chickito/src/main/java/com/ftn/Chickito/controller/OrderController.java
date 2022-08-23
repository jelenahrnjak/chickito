package com.ftn.Chickito.controller;

import com.ftn.Chickito.dto.order.CreateOrderDto;
import com.ftn.Chickito.dto.order.OrderDto;
import com.ftn.Chickito.dto.order.OrderViewDto;
import com.ftn.Chickito.mapper.OrderMapper;
import com.ftn.Chickito.model.Order;
import com.ftn.Chickito.service.OrderService;
import com.ftn.Chickito.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final TokenUtils tokenUtils;
    private final OrderMapper mapper;

    private static final String WHITESPACE = " ";

    @PostMapping
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity<OrderDto> createOrder(@RequestHeader("Authorization") String jwtToken, @RequestBody CreateOrderDto createOrderDto) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(mapper.orderToOrderDto(orderService.createOrder(username, createOrderDto)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}/approve")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public ResponseEntity<Boolean> approveOrder(@RequestHeader("Authorization") String jwtToken, @PathVariable Long id) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(orderService.approveOrder(username, id), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/decline")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public ResponseEntity<Boolean> declineOrder(@RequestHeader("Authorization") String jwtToken, @PathVariable Long id) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        return new ResponseEntity<>(orderService.declineOrder(username, id), HttpStatus.OK);
    }

    @GetMapping("/findAllByAuthor")
    @PreAuthorize("hasAuthority('LEADER')")
    public ResponseEntity<List<OrderViewDto>> findAllByAuthor(@RequestHeader("Authorization") String jwtToken) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        List<Order> orders = orderService.findAllByAuthor(username);
        List<OrderViewDto> orderViews = new ArrayList<>();
        for(Order order : orders){
            orderViews.add(mapper.orderToOrderViewDto(order));
        }

        return new ResponseEntity<>(orderViews, HttpStatus.OK);
    }

    @GetMapping("/findAllByDirector")
    @PreAuthorize("hasAuthority('DIRECTOR')")
    public ResponseEntity<List<OrderViewDto>> findAllByDirector(@RequestHeader("Authorization") String jwtToken) {

        String username = tokenUtils.getUsernameFromToken(jwtToken.split(WHITESPACE)[1]);

        List<Order> orders = orderService.findAllByDirector(username);
        List<OrderViewDto> orderViews = new ArrayList<>();
        for(Order order : orders){
            orderViews.add(mapper.orderToOrderViewDto(order));
        }

        return new ResponseEntity<>(orderViews, HttpStatus.OK);
    }
}
