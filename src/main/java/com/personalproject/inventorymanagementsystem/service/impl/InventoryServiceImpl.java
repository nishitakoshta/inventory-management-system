package com.personalproject.inventorymanagementsystem.service.impl;
import com.personalproject.inventorymanagementsystem.configuration.StockUpdateHandler;
import com.personalproject.inventorymanagementsystem.model.InventoryTransaction;
import com.personalproject.inventorymanagementsystem.model.Products;
import com.personalproject.inventorymanagementsystem.model.Report;
import com.personalproject.inventorymanagementsystem.repository.InventoryTransactionRepository;
import com.personalproject.inventorymanagementsystem.repository.ProductsRepository;
import com.personalproject.inventorymanagementsystem.repository.ReportRepository;
import com.personalproject.inventorymanagementsystem.service.EmailService;
import com.personalproject.inventorymanagementsystem.service.InventoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class InventoryServiceImpl implements InventoryService {
    private final ProductsRepository productsRepository;
    private final InventoryTransactionRepository transactionRepository;
    private final ReportRepository reportRepository;
    private final StockUpdateHandler stockUpdateHandler;
    private final EmailService emailService;
    public InventoryServiceImpl(ProductsRepository productsRepository, InventoryTransactionRepository transactionRepository,
                                ReportRepository reportRepository, StockUpdateHandler stockUpdateHandler,
                                EmailService emailService){
        this.productsRepository = productsRepository;
        this.transactionRepository = transactionRepository;
        this.reportRepository = reportRepository;
        this.stockUpdateHandler = stockUpdateHandler;
        this.emailService = emailService;
    }
    public void checkLowStockAndAlert(Products product) {
        int lowStockThreshold = 10;
        if (product.getStockQuantity() < lowStockThreshold) {
            emailService.sendLowStockAlert(product.getName(), product.getStockQuantity());
        }
    }

    @Override
    public void addStock(Integer productId, int quantity) {
        Products product = productsRepository.findById(productId).orElseThrow();
        product.setStockQuantity(product.getStockQuantity() + quantity);
        productsRepository.save(product);
        checkLowStockAndAlert(product);
        // Notify WebSocket clients about the stock update
        stockUpdateHandler.sendStockUpdate(product.getName(), product.getStockQuantity());
        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setProduct(product);
        transaction.setQuantity(quantity);
        transaction.setTransactionType("add");
        transactionRepository.save(transaction);
    }

    @Override
    public void subtractStock(Integer productId, int quantity) {
        Products product = productsRepository.findById(productId).orElseThrow();
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productsRepository.save(product);
        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setProduct(product);
        transaction.setQuantity(quantity);
        transaction.setTransactionType("subtract");
        transactionRepository.save(transaction);
    }

    @Override
    public void generateReport(HttpServletResponse response) throws IOException {
        try {
            List<Products> products = productsRepository.findAll();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Inventory Report");
            // Header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Product ID");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Stock Quantity");
            // Data rows
            int rowCount = 1;
            for (Products product : products) {
                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getName());
                row.createCell(2).setCellValue(product.getStockQuantity());
            }
            // Save report log
            Report report = new Report();
            report.setReportType("Inventory Report");
            report.setGeneratedAt(LocalDateTime.now());
            report.setGeneratedBy("Admin");
            reportRepository.save(report);
            // Write to response output stream
            response.setHeader("Content-Disposition", "attachment; filename=inventory_report.xlsx");
            workbook.write(response.getOutputStream());
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

}
