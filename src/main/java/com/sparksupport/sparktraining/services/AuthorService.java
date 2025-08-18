package com.sparksupport.sparktraining.services;

import com.sparksupport.sparktraining.entity.Author;
import com.sparksupport.sparktraining.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    /**
     * testing doc -> Inner Documentation example
     *
     * @param author ttyyy
     * @return author
     */
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
