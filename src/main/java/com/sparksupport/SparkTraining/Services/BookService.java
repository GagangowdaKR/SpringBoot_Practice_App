package com.sparksupport.SparkTraining.Services;

import com.sparksupport.SparkTraining.Entity.Book;
import com.sparksupport.SparkTraining.Repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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

    public List<Map<String,String>> getBookAuthors(){
        return bookRepository.findBookAuthors().stream()
                .map(anyTypeObj ->{
                    Object[] object = (Object[]) anyTypeObj;
                    Map<String,String> map = new HashMap<>();
                    map.put("Author", String.valueOf(object[1]));
                    map.put("BookTitle", String.valueOf(object[0]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, String>> getBooksNotBorrowed(){
        return bookRepository.findBookNotBorrowed().stream()
                .map(anyTypeObj ->{
                    Object[] object = (Object[]) anyTypeObj;
                    Map<String,String> map = new HashMap<>();
                    map.put("Book Id", String.valueOf(object[0]));
                    map.put("Book Title", String.valueOf(object[1]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public Page<Book> getBooksInPages(int page, int size, String sortBy){
        Sort sort = Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Book> book = bookRepository.findAll(pageRequest);
        return book;
    }


    public List<Book> getBookNeverBorrowed() {
        return bookRepository.findBookNeverBorrowed();
    }
}
