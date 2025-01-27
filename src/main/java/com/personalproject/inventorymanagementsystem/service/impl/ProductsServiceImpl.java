package com.personalproject.inventorymanagementsystem.service.impl;
import com.personalproject.inventorymanagementsystem.model.ProductPerformance;
import com.personalproject.inventorymanagementsystem.model.Products;
import com.personalproject.inventorymanagementsystem.repository.InventoryTransactionRepository;
import com.personalproject.inventorymanagementsystem.service.ProductsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductsServiceImpl implements ProductsService {

    private final InventoryTransactionRepository inventoryTransactionRepository;
    public ProductsServiceImpl(InventoryTransactionRepository inventoryTransactionRepository){
        this.inventoryTransactionRepository = inventoryTransactionRepository;
    }

    @Override
    public List<ProductPerformance> getProductPerformance() {
        // Fetch data from the repository
        List<Object[]> results = inventoryTransactionRepository.getProductPerformanceData();

        // Create a list to hold the product performance data
        List<ProductPerformance> productPerformances = new ArrayList<>();

        // Loop through the results and create ProductPerformance objects
        for (Object[] result : results) {
            Integer productId = (Integer) result[0];
            String productName = (String) result[1];
            Long totalPerformance = (Long) result[2];

            // Add a new ProductPerformance object to the list
            productPerformances.add(new ProductPerformance(productId, productName, totalPerformance));
        }

        return productPerformances;
    }

}
