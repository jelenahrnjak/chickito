package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.machineMaintenance.MachineMaintenanceViewDto;
import com.ftn.Chickito.dto.order.OrderReportDto;
import com.ftn.Chickito.model.MachineMaintenance;
import com.ftn.Chickito.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final Environment env;

    @Override
    public void sendOrderPdf(String email, DataSource attachment, OrderReportDto order) throws MessagingException {

        String text = "Poštovani,<br>U prilogu se nalazi narudžbenica broj #"+ order.getId() + ".";

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(email);
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("Narudžbenica broj #" + order.getId());
        helper.setText(text, true);

        helper.addAttachment("narudzbenica" + order.getId() + ".pdf",attachment);

        javaMailSender.send(mail);
    }

    @Override
    public void sendMachineMaintenancePdf(String email, DataSource attachment, Integer year) throws MessagingException {

        String text = "Poštovani, <br>U prilogu se nalazi plan ordžavanja za "+ year + ". godinu.";

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(email);
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("Plan ordžavanja za "+ year + ". godinu");
        helper.setText(text, true);

        helper.addAttachment("plan-odrzavanja-" + year + ".pdf",attachment);

        javaMailSender.send(mail);
    }

}
