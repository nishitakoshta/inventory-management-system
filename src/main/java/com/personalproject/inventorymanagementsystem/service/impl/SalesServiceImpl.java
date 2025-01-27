package com.personalproject.inventorymanagementsystem.service.impl;
import com.personalproject.inventorymanagementsystem.model.Products;
import com.personalproject.inventorymanagementsystem.model.Sale;
import com.personalproject.inventorymanagementsystem.repository.ProductsRepository;
import com.personalproject.inventorymanagementsystem.repository.SaleRepository;
import com.personalproject.inventorymanagementsystem.service.InventoryService;
import com.personalproject.inventorymanagementsystem.service.SalesService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class SalesServiceImpl implements SalesService {
    private final SaleRepository saleRepository;
    private final ProductsRepository productsRepository;
    private final InventoryServiceImpl inventoryServiceImpl;
    public SalesServiceImpl(SaleRepository saleRepository, ProductsRepository productsRepository,
                            InventoryServiceImpl inventoryServiceImpl){
        this.productsRepository = productsRepository;
        this.saleRepository = saleRepository;
        this.inventoryServiceImpl = inventoryServiceImpl;
    }
    @Override
    public Sale recordSale(Sale sale, Model model) {
        Products product = sale.getProduct();
        if (product.getStockQuantity() < sale.getQuantitySold()) {
            model.addAttribute("errorMessage", "Insufficient stock available.");
            return null; // Return null or handle accordingly if no sale can be processed
        }
        sale.setSaleDate(LocalDateTime.now());
        // Update product stock after the sale
        product.setStockQuantity(product.getStockQuantity() - sale.getQuantitySold());
        productsRepository.save(product);
        Sale updatedSale = saleRepository.save(sale);
        inventoryServiceImpl.checkLowStockAndAlert(product);
        return updatedSale;
    }
    @Override
    public List<Sale> getSalesInPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return saleRepository.findBySaleDateBetween(startDate, endDate);
    }
    @Override
    public BigDecimal getTotalSales(LocalDateTime startDate, LocalDateTime endDate) {
        List<Sale> sales = getSalesInPeriod(startDate, endDate);
        return sales.stream()
                .map(Sale::getSalePrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    @Override
    public void generateSalesReport(HttpServletResponse response, LocalDateTime startDate, LocalDateTime endDate) throws IOException {
        try {
            List<Sale> sales = getSalesInPeriod(startDate, endDate);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sales Report");

            // Header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Sale ID");
            header.createCell(1).setCellValue("Product Name");
            header.createCell(2).setCellValue("Quantity Sold");
            header.createCell(3).setCellValue("Sale Price");
            header.createCell(4).setCellValue("Sale Date");

            // Data rows
            int rowCount = 1;
            for (Sale sale : sales) {
                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue(sale.getId());
                row.createCell(1).setCellValue(sale.getProduct().getName());
                row.createCell(2).setCellValue(sale.getQuantitySold());
                row.createCell(3).setCellValue(sale.getSalePrice().doubleValue());
                row.createCell(4).setCellValue(sale.getSaleDate().toString());
            }

            // Write to response output stream
            response.setHeader("Content-Disposition", "attachment; filename=sales_report.xlsx");
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

