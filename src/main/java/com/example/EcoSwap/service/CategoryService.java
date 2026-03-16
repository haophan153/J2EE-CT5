package com.example.EcoSwap.service;

import com.example.EcoSwap.entity.Category;
import com.example.EcoSwap.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
