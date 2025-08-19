package com.sparksupport.sparktraining.controllers.api;

import com.sparksupport.sparktraining.entity.Author;
import com.sparksupport.sparktraining.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {


    private final AuthorService authorService;

    AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

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

    @GetMapping("/author/{id}")
    public Author getAuthors(@PathVariable int id) {
        return authorService.getAuthorById(id);
    }
}
