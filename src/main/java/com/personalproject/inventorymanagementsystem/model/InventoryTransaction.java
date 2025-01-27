package com.personalproject.inventorymanagementsystem.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "inventory_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Products product;
    private int quantity;
    private String transactionType; // "add" or "subtract"
    private LocalDateTime transactionDate;
}
