package com.sparksupport.SparkTraining.Services;

import com.sparksupport.SparkTraining.Entity.Author;
import com.sparksupport.SparkTraining.Entity.Book;
import com.sparksupport.SparkTraining.Repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> addAuthors(List<Author> author) {
        return authorRepository.saveAll(author);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
    }
}
