package com.personalproject.inventorymanagementsystem.controller;
import com.personalproject.inventorymanagementsystem.service.InventoryService;
import com.personalproject.inventorymanagementsystem.service.ProductsService;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/analytics")
public class AnalyticsController {

    private final ProductsService productsService;
    public AnalyticsController(ProductsService productsService){
        this.productsService = productsService;
    }

    @GetMapping
    public String viewAnalytics(Model model) {
        model.addAttribute("productPerformance", productsService.getProductPerformance());
        return "analytics-dashboard";
    }
}

