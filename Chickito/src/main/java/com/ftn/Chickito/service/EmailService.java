package com.ftn.Chickito.service;

import com.ftn.Chickito.dto.order.OrderReportDto;

import javax.activation.DataSource;
import javax.mail.MessagingException;

public interface EmailService {

    void sendOrderReport(String email, DataSource attachment, OrderReportDto order) throws MessagingException;
}
