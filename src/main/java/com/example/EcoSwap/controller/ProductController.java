package com.example.EcoSwap.controller;

import com.example.EcoSwap.entity.Category;
import com.example.EcoSwap.entity.Product;
import com.example.EcoSwap.entity.User;
import com.example.EcoSwap.service.CategoryService;
import com.example.EcoSwap.service.FileUploadService;
import com.example.EcoSwap.service.ProductService;
import com.example.EcoSwap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final FileUploadService fileUploadService;
    
    @GetMapping("/products")
    public String products(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("products", productService.getAvailableProducts(org.springframework.data.domain.PageRequest.of(page, 12)).getContent());
        return "products/list";
    }
    
    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        productService.getProductById(id).ifPresent(product -> model.addAttribute("product", product));
        return "products/detail";
    }
    
    @GetMapping("/product/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "products/create";
    }
    
    @PostMapping("/product/new")
    public String createProduct(@ModelAttribute Product product, 
                                @RequestParam("imageFile") MultipartFile imageFile,
                                HttpSession session) {
        // Upload ảnh nếu có
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = fileUploadService.uploadFile(imageFile);
            if (imageUrl != null) {
                product.setImageUrl(imageUrl);
            }
        }
        
        userService.getUserById(1L).ifPresent(user -> product.setUser(user));
        product.setStatus("AVAILABLE");
        productService.createProduct(product);
        return "redirect:/products";
    }
}
