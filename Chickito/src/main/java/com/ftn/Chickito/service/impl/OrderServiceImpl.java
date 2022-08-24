package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.order.CreateOrderDto;
import com.ftn.Chickito.dto.order.OrderReportDto;
import com.ftn.Chickito.exception.OrderAlreadyProccesedException;
import com.ftn.Chickito.exception.WrongReviewerException;
import com.ftn.Chickito.mapper.OrderMapper;
import com.ftn.Chickito.model.*;
import com.ftn.Chickito.repository.*;
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
    private final CompanyRepository companyRepository;
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
    public String exportOrderReport(String username, Long id) throws FileNotFoundException, JRException, MessagingException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with username = %s doesn't exist.", username)));

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id = %s doesn't exist.", id)));
        OrderReportDto orderDto = mapper.orderToOrderReportDto(order);

        List<OrderReportDto> orders = new ArrayList<>();
        orders.add(orderDto);
        File file = ResourceUtils.getFile("classpath:reportTemplates\\OrderReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
        DataSource attachment =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");

        this.emailService.sendOrderReport(user.getEmail(), attachment, orderDto);

        return "ok";
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
