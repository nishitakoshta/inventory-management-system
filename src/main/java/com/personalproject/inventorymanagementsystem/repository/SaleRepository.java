package com.personalproject.inventorymanagementsystem.repository;
import com.personalproject.inventorymanagementsystem.model.Products;
import com.personalproject.inventorymanagementsystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Sale> findByProduct(Products product);
}
