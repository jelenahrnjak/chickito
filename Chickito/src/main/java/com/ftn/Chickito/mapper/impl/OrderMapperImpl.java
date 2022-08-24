package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.machine.MachineDto;
import com.ftn.Chickito.dto.order.OrderDto;
import com.ftn.Chickito.dto.order.OrderItemDto;
import com.ftn.Chickito.dto.order.OrderReportDto;
import com.ftn.Chickito.dto.order.OrderViewDto;
import com.ftn.Chickito.mapper.*;
import com.ftn.Chickito.model.Building;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.Order;
import com.ftn.Chickito.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {

    private final MachineMapper machineMapper;
    private final UserMapper userMapper;
    private final SectorMapper sectorMapper;
    private final AddressMapper addressMapper;
    private final BuildingRepository buildingRepository;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return OrderViewDto.builder()
                .id(order.getId())
                .orderItems(orderItemsToOrderItemsDto(order.getMachines()))
                .price(order.getPrice())
                .author(order.getAuthor().getFirstName() + " " + order.getAuthor().getLastName())
                .reviewer(order.getReviewer().getFirstName() + " " + order.getAuthor().getLastName())
                .sector(sectorMapper.sectorTypeToString(order.getAuthor().getSector().getType()))
                .creationDate(order.getCreationDate() == null ? "" : order.getCreationDate().format(formatter))
                .approved(order.getApproved())
                .build();
    }

    @Override
    public List<OrderViewDto> orderListToOrderViewDtoList(List<Order> orders) {
        List<OrderViewDto> orderViews = new ArrayList<>();
        for(Order order : orders){
            orderViews.add(this.orderToOrderViewDto(order));
        }
        return orderViews;
    }

    List<OrderItemDto> orderItemsToOrderItemsDto(Set<Machine> machines){
        List<OrderItemDto> items = new ArrayList<>();

        for(Machine machine : machines){
            items.add(machineToOrderItemDto(machine));
        }

        return items;
    }

    @Override
    public OrderItemDto machineToOrderItemDto(Machine machine) {


        return OrderItemDto.builder()
                .name(machine.getName())
                .model(machine.getModel())
                .serialNumber(machine.getSerialNumber())
                .documentation(machine.getDocumentation() == null ? "" : machine.getDocumentation().getText())
                .price(machine.getPrice())
                .quantity(machine.getQuantity())
                .build();
    }

    @Override
    public OrderReportDto orderToOrderReportDto(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Building headOffice = this.buildingRepository.findHeadOfficeOfCompany(order.getAuthor().getSector().getCompany().getId());

        return OrderReportDto.builder()
                .id("#" + order.getId().toString())
                .orderItems(orderItemsToOrderItemsDto(order.getMachines()))
                .companyName(order.getAuthor().getSector().getCompany().getName())
                .creationDate(order.getCreationDate() == null ? "" : order.getCreationDate().format(formatter))
                .price(order.getPrice())
                .author(order.getAuthor().getFirstName() + " " + order.getAuthor().getLastName())
                .reviewer(order.getReviewer().getFirstName() + " " + order.getAuthor().getLastName())
                .sector(sectorMapper.sectorTypeToString(order.getAuthor().getSector().getType()))
                .creationDate(order.getCreationDate() == null ? "" : order.getCreationDate().format(formatter))
                .approved(order.getApproved())
                .headOffice(headOffice == null ? "" : this.addressMapper.getAddressString(headOffice.getAddress()))
                .build();
    }
}
