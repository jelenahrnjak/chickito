package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.dto.order.OrderDto;
import com.ftn.Chickito.mapper.MachineMapper;
import com.ftn.Chickito.mapper.OrderMapper;
import com.ftn.Chickito.mapper.UserMapper;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
