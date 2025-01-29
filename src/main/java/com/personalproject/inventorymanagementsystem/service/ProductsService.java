package com.personalproject.inventorymanagementsystem.service;
import com.personalproject.inventorymanagementsystem.model.ProductPerformance;
import com.personalproject.inventorymanagementsystem.model.Products;

import java.util.List;
public interface ProductsService {
    List<ProductPerformance> getProductPerformance();
    List<Products> getProductList();
}
