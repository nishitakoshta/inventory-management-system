package com.personalproject.inventorymanagementsystem.repository;
import com.personalproject.inventorymanagementsystem.model.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Integer> {
    @Query(nativeQuery = true, value = "SELECT p.id, p.name, SUM(s.quantity_sold) AS total_quantity_sold FROM sales s JOIN products p ON p.id = s.product_id\n" +
            "GROUP BY p.id, p.name order by total_quantity_sold desc;")
    List<Object[]> getProductPerformanceData();
}
