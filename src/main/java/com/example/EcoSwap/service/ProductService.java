package com.example.EcoSwap.service;

import com.example.EcoSwap.entity.Product;
import com.example.EcoSwap.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Page<Product> getAvailableProducts(Pageable pageable) {
        return productRepository.findByStatus("AVAILABLE", pageable);
    }
    
    public Page<Product> getProductsByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable);
    }
    
    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }
    
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    public List<Product> getProductsByUser(Long userId) {
        return productRepository.findByUserId(userId);
    }
    
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Transactional
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
