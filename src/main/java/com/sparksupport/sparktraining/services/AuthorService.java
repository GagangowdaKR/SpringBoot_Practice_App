package com.sparksupport.sparktraining.services;

import com.sparksupport.sparktraining.entity.Author;
import com.sparksupport.sparktraining.exceptions.InvalidRequestException;
import com.sparksupport.sparktraining.exceptions.NullValueException;
import com.sparksupport.sparktraining.exceptions.ResourceNotFoundException;
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
        if (author == null) throw new NullValueException("Author cannot be null");
        return authorRepository.save(author);
    }

    /**
     * testing doc -> Inner Documentation example
     *
     * @param author ttyyy
     * @return author
     */
    public List<Author> addAuthors(List<Author> author) {
        if (author == null) throw new NullValueException("At least one or more Authors needed but found null");
        return authorRepository.saveAll(author);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer authorId) {
        if (authorId <= 0) {
            throw new InvalidRequestException("Author id should be greater than 0, can't be -ve");
        }
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found, id = " + authorId));
    }
}
