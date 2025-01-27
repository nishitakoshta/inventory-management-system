package com.personalproject.inventorymanagementsystem.service;
import com.personalproject.inventorymanagementsystem.model.Sale;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
public interface SalesService {
    Sale recordSale(Sale sale, Model model);
    List<Sale> getSalesInPeriod(LocalDateTime startDate, LocalDateTime endDate);
    BigDecimal getTotalSales(LocalDateTime startDate, LocalDateTime endDate);
    void generateSalesReport(HttpServletResponse response, LocalDateTime startDate, LocalDateTime endDate) throws IOException;
}
