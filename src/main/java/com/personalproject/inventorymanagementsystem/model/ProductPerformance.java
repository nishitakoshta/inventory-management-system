package com.personalproject.inventorymanagementsystem.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPerformance {
    private Integer productId;
    private String productName;
    private Long totalSales;
}
