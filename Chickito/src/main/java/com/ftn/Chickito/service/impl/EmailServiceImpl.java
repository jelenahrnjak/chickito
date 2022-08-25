package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.order.OrderReportDto;
import com.ftn.Chickito.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    @Override
    public void sendOrderReport(String email, DataSource attachment, OrderReportDto order) throws MessagingException {

        String text = "<br>U prilogu se nalazi narudžbenica broj #"+ order.getId() + ".";

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(email);
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("Narudžbenica broj #" + order.getId());
        helper.setText("Poštovani," + text, true);

        helper.addAttachment("narudzbenica" + order.getId() + ".pdf",attachment);

        javaMailSender.send(mail);
    }

}
