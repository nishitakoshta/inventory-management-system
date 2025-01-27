package com.personalproject.inventorymanagementsystem.controller;
import com.personalproject.inventorymanagementsystem.model.Products;
import com.personalproject.inventorymanagementsystem.model.Sale;
import com.personalproject.inventorymanagementsystem.repository.ProductsRepository;
import com.personalproject.inventorymanagementsystem.service.SalesService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Controller
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;
    private final ProductsRepository productsRepository;
    public SalesController(SalesService salesService, ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
        this.salesService = salesService;
    }

    @GetMapping("/create")
    public String createSaleForm(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("products", productsRepository.findAll());  // Get all products
        return "sales/create";
    }

    @PostMapping
    public String recordSale(@ModelAttribute Sale sale, Model model) {
        salesService.recordSale(sale, model);
        return "redirect:/sales";  // Redirect to sales listing
    }

    @GetMapping
    public String listSales(Model model) {
        List<Sale> sales = salesService.getSalesInPeriod(LocalDateTime.now().minusMonths(1), LocalDateTime.now());
        model.addAttribute("sales", sales);
        return "sales/list";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        BigDecimal totalSales = salesService.getTotalSales(LocalDateTime.now().minusMonths(1), LocalDateTime.now());
        List<Products> mostSoldProducts = productsRepository.findTop5MostSoldProductsNative();  // Custom query to get top 5
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("mostSoldProducts", mostSoldProducts);
        return "dashboard/index";  // Dashboard page
    }

    @GetMapping("/report")
    public String salesReport(Model model) {
        return "sales/report";
    }

    @GetMapping("/downloadReport")
    public void downloadSalesReport(HttpServletResponse response) throws IOException {
        salesService.generateSalesReport(response, LocalDateTime.now().minusMonths(1), LocalDateTime.now());
    }
}

