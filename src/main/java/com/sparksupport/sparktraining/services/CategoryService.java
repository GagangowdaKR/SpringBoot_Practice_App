package com.sparksupport.sparktraining.services;

import com.sparksupport.sparktraining.entity.Category;
import com.sparksupport.sparktraining.exceptions.CategoryNotFoundException;
import com.sparksupport.sparktraining.exceptions.InvalidRequestException;
import com.sparksupport.sparktraining.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> addCategories(@Valid List<Category> categories) {
        return  categoryRepository.saveAll(categories);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer categoryId) {
        if (categoryId <= 0) {
            throw new InvalidRequestException("Category id must be greater than 0");
        }
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category id " + categoryId + " not found"));
    }
}
