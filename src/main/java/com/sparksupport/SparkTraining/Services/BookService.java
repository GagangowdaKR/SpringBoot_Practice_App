package com.sparksupport.SparkTraining.Services;

import com.sparksupport.SparkTraining.Entity.Book;
import com.sparksupport.SparkTraining.Repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Book> searchBookTitleLike(String name){
        return bookRepository.findByTitleLike('%'+name+'%');
    }

    public List<Book> searchBookTitleStartWith(String title) {
        return bookRepository.findByTitleStartWith(title);
    }

    public List<Book> searchBookEndsWith(String title){
        return bookRepository.findByTitleEndingWith(title);
    }

    public List<?> getBooksAuthorCategory() {
        return bookRepository.findAllBooksAuthorCategory();
    }

    public List<Map<String,String>> getBooksCountInCategory() {
        return bookRepository.countBooksInCategory().stream()
                .map((obj) ->{
                    Map<String, String> map = new HashMap<>();
                    map.put("Category", String.valueOf(obj[0]));
                    map.put("BookCount", String.valueOf(obj[1]));
                    return map;
                })
                .collect(Collectors.toList());
    }

}
