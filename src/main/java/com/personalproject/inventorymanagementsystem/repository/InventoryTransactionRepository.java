package com.personalproject.inventorymanagementsystem.repository;
import com.personalproject.inventorymanagementsystem.model.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Integer> {
    @Query("SELECT p.id, p.name, SUM(CASE WHEN it.transactionType = 'add' THEN it.quantity ELSE -it.quantity END) " +
            "FROM InventoryTransaction it JOIN it.product p GROUP BY p.id")
    List<Object[]> getProductPerformanceData();
}
