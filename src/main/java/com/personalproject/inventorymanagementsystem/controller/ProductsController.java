package com.personalproject.inventorymanagementsystem.controller;
import com.personalproject.inventorymanagementsystem.model.Products;
import com.personalproject.inventorymanagementsystem.repository.ProductsRepository;
import com.personalproject.inventorymanagementsystem.service.ProductsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsRepository productsRepository;
    private final ProductsService productsService;
    public ProductsController(ProductsRepository productsRepository, ProductsService productsService){
        this.productsRepository = productsRepository;
        this.productsService = productsService;
    }
    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Products());
        return "add-products";
    }
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Products product, Model model) {
        productsRepository.save(product);
        model.addAttribute("message", "Product added successfully!");
        return "redirect:/inventory"; // Redirect back to the inventory page
    }
    @GetMapping("/productList")
    public String getProductList(Model model){
        model.addAttribute("products", productsService.getProductList());
        return "product-list";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Products product = productsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        return "update-product"; // Display the update product form
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") @Valid Products product, BindingResult result) {
        // Check if there are validation errors
        if (result.hasErrors()) {
            return "update-product"; // If errors, return to the form with error messages
        }

        // Save the updated product in the database
        productsRepository.save(product);
        return "redirect:/products/productList"; // Redirect to the product list page after updating
    }

}
