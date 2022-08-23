package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.order.CreateOrderDto;
import com.ftn.Chickito.exception.OrderAlreadyProccesedException;
import com.ftn.Chickito.exception.WrongReviewerException;
import com.ftn.Chickito.model.*;
import com.ftn.Chickito.repository.*;
import com.ftn.Chickito.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CompanyRepository companyRepository;
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
                    .serialNumber(null)
                    .active(false)
                    .documentation(getDocumentation(orderItemDto.getDocumentation()))
                    .price(orderItemDto.getPrice())
                    .quantity(orderItemDto.getQuantity())
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
                .creationDate(LocalDateTime.now())
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

    @Override
    public List<Order> findAllByAuthor(String authorUsername){
        return this.orderRepository.findAllByAuthor(authorUsername);
    }

    @Override
    public List<Order> findAllByDirector(String directorUsername) {

        Company company = companyRepository.findByDirector(directorUsername);
        return this.orderRepository.findAllByCompany(company.getId());
    }
}
