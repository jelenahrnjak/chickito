package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.dto.order.OrderDto;
import com.ftn.Chickito.dto.order.OrderItemDto;
import com.ftn.Chickito.dto.order.OrderViewDto;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.mapper.OrderMapper;
import com.ftn.Chickito.mapper.UserMapper;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {

    private final MachineMapper machineMapper;
    private final UserMapper userMapper;

    @Override
    public OrderDto orderToOrderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .machines(mapMachines(order.getMachines()))
                .price(order.getPrice())
                .author(userMapper.userToUserDto(order.getAuthor()))
                .reviewer(userMapper.userToUserDto(order.getReviewer()))
                .build();
    }

    private Set<MachineDto> mapMachines(Set<Machine> machines) {
        return machines.stream().map(machineMapper::machineToMachineDto).collect(Collectors.toSet());
    }

    @Override
    public OrderViewDto orderToOrderViewDto(Order order) {

        String formattedDateTime = "";
        if(order.getCreationDate() != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            formattedDateTime = order.getCreationDate().format(formatter);
        }

        List<OrderItemDto> items = new ArrayList<>();

        for(Machine machine : order.getMachines()){
            items.add(machineToOrderItemDto(machine));
        }

        return OrderViewDto.builder()
                .id(order.getId())
                .orderItems(items)
                .price(order.getPrice())
                .author(order.getAuthor().getFirstName() + " " + order.getAuthor().getLastName())
                .reviewer(order.getReviewer().getFirstName() + " " + order.getAuthor().getLastName())
                .creationDate(formattedDateTime)
                .approved(order.getApproved())
                .build();
    }

    @Override
    public OrderItemDto machineToOrderItemDto(Machine machine) {

        String documentation = "";
        if(machine.getDocumentation() != null){
            documentation = machine.getDocumentation().getText();
        }

        return OrderItemDto.builder()
                .name(machine.getName())
                .model(machine.getModel())
                .serialNumber(machine.getSerialNumber())
                .documentation(documentation)
                .price(machine.getPrice())
                .build();
    }
}
