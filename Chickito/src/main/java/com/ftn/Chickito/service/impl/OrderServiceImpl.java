package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.order.CreateOrderDto;
import com.ftn.Chickito.exception.OrderAlreadyProccesedException;
import com.ftn.Chickito.exception.WrongReviewerException;
import com.ftn.Chickito.model.Documentation;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.Order;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.repository.DocumentationRepository;
import com.ftn.Chickito.repository.MachineRepository;
import com.ftn.Chickito.repository.OrderRepository;
import com.ftn.Chickito.repository.UserRepository;
import com.ftn.Chickito.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MachineRepository machineRepository;
    private final UserRepository userRepository;
    private final DocumentationRepository documentationRepository;

    private final static String LEADER_ROLE = "LEADER";

    @Override
    public Order createOrder(String authorUsername, CreateOrderDto createOrderDto) {

        Set<Machine> machines = new HashSet<>();
        User author = getLeader(authorUsername);

        createOrderDto.getOrderItems().forEach(orderItemDto -> {

            Machine machine = Machine.builder()
                    .name(orderItemDto.getName())
                    .model(orderItemDto.getModel())
                    .serialNumber(orderItemDto.getSerialNumber())
                    .active(false)
                    .documentation(getDocumentation(orderItemDto.getDocumentation()))
                    .price(orderItemDto.getPrice())
                    .sector(author.getSector())
                    .build();

            machines.add(machine);
        });

        double priceSum = machines.stream().mapToDouble(Machine::getPrice).sum();
        User reviewer = author.getSector().getCompany().getDirector();

        Order order = Order.builder()
                .machines(machines)
                .approved(null)
                .author(author)
                .reviewer(reviewer)
                .price(priceSum)
                .build();

        machines.forEach(machine -> machine.setOrder(order));
        orderRepository.save(order);

        return order;
    }

    @Override
    public boolean approveOrder(String reviewerUsername, Long id) {

        Order order = getOrder(id);

        verifyReviewerIsAccurate(order.getReviewer(), reviewerUsername);

        order.getMachines().forEach(machine -> {
            machine.setActive(true);
            machineRepository.save(machine);
        });

        order.setApproved(true);
        orderRepository.save(order);

        return true;
    }

    @Override
    public boolean declineOrder(String reviewerUsername, Long id) {

        Order order = getOrder(id);

        verifyReviewerIsAccurate(order.getReviewer(), reviewerUsername);

        order.setApproved(false);
        orderRepository.save(order);

        return true;
    }

    private void verifyReviewerIsAccurate(User reviewer, String reviewerUsername) {
        if (!reviewer.getUsername().equals(reviewerUsername)) {
            throw new WrongReviewerException();
        }
    }

    private Order getOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id = %s doesn't exist.", id)));

        if (order.getApproved() != null) {
            throw new OrderAlreadyProccesedException(id);
        }

        return order;
    }

    private User getLeader(String username) {
        return userRepository.findByUsernameAndRoleName(username, LEADER_ROLE)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Leader with username = %s doesn't exist.", username)));
    }

    private Documentation getDocumentation(String documentation) {

        if (documentation == null || documentation.equals("")) {
            return null;
        }

        return documentationRepository.findByText(documentation)
                .orElse(documentationRepository.save(Documentation.builder().text(documentation).build()));
    }
}
