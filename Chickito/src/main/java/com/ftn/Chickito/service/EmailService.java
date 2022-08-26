package com.ftn.Chickito.service;

import com.ftn.Chickito.dto.machineMaintenance.MachineMaintenanceViewDto;
import com.ftn.Chickito.dto.order.OrderReportDto;

import javax.activation.DataSource;
import javax.mail.MessagingException;

public interface EmailService {

    void sendOrderPdf(String email, DataSource attachment, OrderReportDto order) throws MessagingException;
    void sendMachineMaintenancePdf(String email, DataSource attachment, Integer year) throws MessagingException;
}
