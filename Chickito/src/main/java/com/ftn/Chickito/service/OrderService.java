package com.ftn.Chickito.service;

import com.ftn.Chickito.dto.order.CreateOrderDto;
import com.ftn.Chickito.model.Order;
import net.sf.jasperreports.engine.JRException;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.List;

public interface OrderService {

    Order createOrder(String authorUsername, CreateOrderDto createOrderDto);
    void exportOrderReport(String username, Long id) throws FileNotFoundException, JRException, MessagingException;
    boolean approveOrder(String reviewerUsername, Long id);

    boolean declineOrder(String reviewerUsername, Long id);
    List<Order> findAllByAuthor(String authorUsername);
    List<Order> findAllByDirector(String directorUsername);
}
