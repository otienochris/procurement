package com.otienochris.procurement_management_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystem;
import java.util.Objects;

@Component
public class MailSendingService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String fromEmail ,
                                String subject,
                                String body) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setTo(toEmail);
        message.setFrom(fromEmail, "Procurement team");
        message.setSubject(subject);
        message.setText(body, true);
        mailSender.send(mimeMessage);
    }

    public void sendEmailWithAttachment(String toEmail,
                                        String fromEmail ,
                                        String subject,
                                        String body,
                                        String attach) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMailMessage= mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

        mimeMessageHelper.setFrom(fromEmail, "Procurement team");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body,true);
        mimeMessageHelper.setSubject(subject);

        if (!attach.isBlank() && !attach.isEmpty()){
            FileSystemResource fileSystemResource = new FileSystemResource(new File(attach));
            mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);
        }

        mailSender.send(mimeMailMessage);
    }
}
