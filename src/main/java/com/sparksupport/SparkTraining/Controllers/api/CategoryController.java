package com.sparksupport.SparkTraining.Controllers.api;

import com.sparksupport.SparkTraining.Entity.Category;
import com.sparksupport.SparkTraining.Services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public Category addCategory(@Valid @RequestBody Category category){
        System.out.println(category);
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
}
