package com.personalproject.inventorymanagementsystem.service;
public interface EmailService {
    void sendLowStockAlert(String productName, int stockQuantity);
}
