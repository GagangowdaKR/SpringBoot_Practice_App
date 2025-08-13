package com.sparksupport.SparkTraining.Services;

import com.sparksupport.SparkTraining.Entity.Book;
import com.sparksupport.SparkTraining.Repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> addBooks(@Valid List<Book> books) {
        return bookRepository.saveAll(books);
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Integer bookId){
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book Not Found"));
    }
}
