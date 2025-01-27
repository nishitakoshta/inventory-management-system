package com.personalproject.inventorymanagementsystem.controller;
import com.personalproject.inventorymanagementsystem.service.InventoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@Controller
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    // View inventory dashboard
    @GetMapping
    public String viewDashboard(Model model) {
        model.addAttribute("products", inventoryService.getAllProducts());
        return "inventory-dashboard";
    }

    // Add stock
    @PostMapping("/add")
    public String addStock(@RequestParam Integer productId, @RequestParam int quantity) {
        inventoryService.addStock(productId, quantity);
        return "redirect:/inventory";
    }

    // Subtract stock
    @PostMapping("/subtract")
    public String subtractStock(@RequestParam Integer productId, @RequestParam int quantity) {
        inventoryService.subtractStock(productId, quantity);
        return "redirect:/inventory";
    }

    // Generate report
    @GetMapping("/report")
    public void generateReport(HttpServletResponse response) throws IOException {
        inventoryService.generateReport(response);
    }
}

