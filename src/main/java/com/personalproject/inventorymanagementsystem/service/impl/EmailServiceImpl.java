package com.personalproject.inventorymanagementsystem.service.impl;
import com.personalproject.inventorymanagementsystem.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    public EmailServiceImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }
    @Override
    public void sendLowStockAlert(String productName, int stockQuantity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("try.nishitakoshta@gmail.com");
        message.setSubject("Low Stock Alert: " + productName);
        message.setText("The stock for " + productName + " is below the threshold. Current stock: " + stockQuantity);
        mailSender.send(message);
    }
}
