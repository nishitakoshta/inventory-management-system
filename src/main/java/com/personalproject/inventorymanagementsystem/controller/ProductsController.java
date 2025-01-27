package com.personalproject.inventorymanagementsystem.controller;
import com.personalproject.inventorymanagementsystem.model.Products;
import com.personalproject.inventorymanagementsystem.repository.ProductsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsRepository productsRepository;
    public ProductsController(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
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
}
