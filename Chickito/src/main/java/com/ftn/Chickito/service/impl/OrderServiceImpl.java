package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.order.CreateOrderDto;
import com.ftn.Chickito.dto.order.OrderReportDto;
import com.ftn.Chickito.exception.OrderAlreadyProcessedException;
import com.ftn.Chickito.exception.WrongReviewerException;
import com.ftn.Chickito.mapper.OrderMapper;
import com.ftn.Chickito.model.Documentation;
import com.ftn.Chickito.model.Machine;
import com.ftn.Chickito.model.Order;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.repository.DocumentationRepository;
import com.ftn.Chickito.repository.MachineRepository;
import com.ftn.Chickito.repository.OrderRepository;
import com.ftn.Chickito.repository.UserRepository;
import com.ftn.Chickito.service.EmailService;
import com.ftn.Chickito.service.OrderService;
import com.sun.istack.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MachineRepository machineRepository;
    private final UserRepository userRepository;
    private final DocumentationRepository documentationRepository;
    private final OrderMapper mapper;
    private final EmailService emailService;

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
                    .technicalTask(orderItemDto.getTechnicalTask())
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
    public void generateOrderPdf(String username, Long id) throws FileNotFoundException, JRException, MessagingException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with username = %s doesn't exist.", username)));

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id = %s doesn't exist.", id)));
        OrderReportDto orderDto = mapper.orderToOrderReportDto(order);

        List<OrderReportDto> orders = new ArrayList<>();
        orders.add(orderDto);
        File file = ResourceUtils.getFile("classpath:reportTemplates\\OrderReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderDto.getOrderItems());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companyName", orderDto.getCompanyName());
        parameters.put("sector", orderDto.getSector());
        parameters.put("headOffice", orderDto.getHeadOffice());
        parameters.put("id", "#" + orderDto.getId().toString());
        parameters.put("author", orderDto.getAuthor());
        parameters.put("reviewer", orderDto.getReviewer());
        parameters.put("creationDate", orderDto.getCreationDate());
        parameters.put("price", orderDto.getPrice());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
        DataSource attachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");

        this.emailService.sendOrderPdf(user.getEmail(), attachment, orderDto);
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
            throw new OrderAlreadyProcessedException(id);
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
    public List<Order> findAllByAuthor(String authorUsername) {
        return this.orderRepository.findAllByAuthor(authorUsername);
    }

    @Override
    public List<Order> findAllByDirector(String directorUsername) {

        return this.orderRepository.findAllByDirector(directorUsername);
    }
}
