package com.sparksupport.sparktraining.services;

import com.sparksupport.sparktraining.entity.Category;
import com.sparksupport.sparktraining.repository.CategoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @BeforeAll
    public static void setup(){
        System.out.println("!!! Before All");
    }

    @BeforeEach
    public void setUpEachTest(){
        System.out.println("! Before Each");
    }

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    void testAddCategory() {
//        Data preparation
        Category category = new Category();
        category.setCategoryId(1);
        category.setName("testCategoryName");

//        Mocking you calls if any
        when(categoryRepository.save(category)).thenReturn(category);

//        Calling actual method
        Category addedCategory = categoryService.addCategory(category);

        System.out.println("I am in addCategory Test method");

//        Assertions
        assertTrue(addedCategory.getCategoryId()==1);
        assertEquals(addedCategory,category);
        assertEquals(addedCategory.getCategoryId(),category.getCategoryId());
        assertEquals(1, category.getCategoryId());
//        assertEquals(2, category.getCategoryId());

        assertEquals(addedCategory.getName(),category.getName());
//        assertEquals(addedCategory.getName()+"45",category.getName());

    }

    @Test
    void testGetCategoryById(){
        Integer categoryId = 2;

        Category category = new Category();
        category.setCategoryId(2);
        category.setName("Test Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Category fetchedCategory = categoryService.getCategoryById(categoryId);

        System.out.println("I am in getCategoryById Test method");

        assertTrue(categoryId == fetchedCategory.getCategoryId());
    }

    @AfterAll
    public static void destroy(){
        System.out.println("After All !!!");
    }

    @AfterEach
    public void cleanup(){
        System.out.println("After Each !");
    }
}