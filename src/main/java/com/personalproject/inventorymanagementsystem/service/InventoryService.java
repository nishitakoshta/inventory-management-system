package com.personalproject.inventorymanagementsystem.service;
import com.personalproject.inventorymanagementsystem.model.Products;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
public interface InventoryService {
    void addStock(Integer productId, int quantity);
    void subtractStock(Integer productId, int quantity);
    void generateReport(HttpServletResponse response) throws IOException;
    List<Products> getAllProducts();
}
