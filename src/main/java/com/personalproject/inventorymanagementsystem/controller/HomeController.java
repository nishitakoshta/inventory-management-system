package com.personalproject.inventorymanagementsystem.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "home"; // This refers to home.html (or home.thymeleaf)
    }

    // Define additional routes to handle the other pages/templates
    @GetMapping("/sales-dashboard")
    public String salesDashboard() {
        return "dashboard/index"; // This refers to sales-dashboard.html (or .thymeleaf)
    }

    @GetMapping("/create")
    public String create() {
        return "sales/create"; // This refers to create.html
    }

    @GetMapping("/list")
    public String list() {
        return "sales"; // This refers to list.html
    }

    @GetMapping("/add-products")
    public String addProducts() {
        return "add-products"; // This refers to add-products.html
    }

    @GetMapping("/analytics-dashboard")
    public String analyticsDashboard() {
        return "analytics-dashboard"; // This refers to analytics-dashboard.html
    }

    @GetMapping("/inventory-dashboard")
    public String inventoryDashboard() {
        return "inventory-dashboard"; // This refers to inventory-dashboard.html
    }

    @GetMapping("/product-list")
    public String productList() {
        return "product-list"; // This refers to product-list.html
    }
}

