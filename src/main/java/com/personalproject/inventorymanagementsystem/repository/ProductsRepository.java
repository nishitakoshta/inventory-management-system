package com.personalproject.inventorymanagementsystem.repository;
import com.personalproject.inventorymanagementsystem.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Query(value = "SELECT p.* FROM products p JOIN sales s ON p.id = s.product_id GROUP BY p.id ORDER BY SUM(s.quantity_sold) DESC LIMIT 5", nativeQuery = true)
    List<Products> findTop5MostSoldProductsNative();
}
