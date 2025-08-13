package com.sparksupport.SparkTraining.Services;

import com.sparksupport.SparkTraining.Entity.Book;
import com.sparksupport.SparkTraining.Entity.BookIssues;
import com.sparksupport.SparkTraining.Repository.BookIssuesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookIssuesService {

    @Autowired
    private BookIssuesRepository bookIssuesRepository;

    public BookIssues addBookIssues(BookIssues bookIssues){
        return bookIssuesRepository.save(bookIssues);
    }

    public List<BookIssues> addBooksIssues(List<BookIssues> booksIssues) {
        return bookIssuesRepository.saveAll(booksIssues);
    }

    public List<BookIssues> getBooksIssues(){
        return bookIssuesRepository.findAll();
    }
}
