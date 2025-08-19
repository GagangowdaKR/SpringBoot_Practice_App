package com.sparksupport.sparktraining.controllers.api;

import com.sparksupport.sparktraining.entity.Category;
import com.sparksupport.sparktraining.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    public Category addCategory(@Valid @RequestBody Category category){
        return categoryService.addCategory(category);
    }
    @PostMapping("/addCategories")
    public List<Category> addCategories(@Valid @RequestBody List<Category> categories){
        return categoryService.addCategories(categories);
    }

    @GetMapping("/getCategories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }
}
