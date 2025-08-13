package com.sparksupport.SparkTraining.Controllers.api;

import com.sparksupport.SparkTraining.Entity.Author;
import com.sparksupport.SparkTraining.Services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public Author addAuthor(@Valid @RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @PostMapping("/addAuthors")
    public List<Author> addAuthors(@Valid @RequestBody List<Author> author){
        return authorService.addAuthors(author);
    }

    @GetMapping("/getAuthors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }
}
