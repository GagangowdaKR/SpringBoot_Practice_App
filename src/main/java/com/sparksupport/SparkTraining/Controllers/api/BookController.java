package com.sparksupport.SparkTraining.Controllers.api;

import com.sparksupport.SparkTraining.Entity.Author;
import com.sparksupport.SparkTraining.Entity.Book;
import com.sparksupport.SparkTraining.Entity.Category;
import com.sparksupport.SparkTraining.Services.AuthorService;
import com.sparksupport.SparkTraining.Services.BookService;
import com.sparksupport.SparkTraining.Services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addBook")
    public Book addBook(@Valid @RequestBody Book book){
        Author author = authorService.getAuthorById(book.getAuthor().getAuthorId());
        Category category = categoryService.getCategoryById(book.getCategory().getCategoryId());
        Book newBook = new Book();
        newBook.setAuthor(author);
        newBook.setCategory(category);
        newBook.setTitle(book.getTitle());
        newBook.setBookId(book.getBookId());
        return bookService.addBook(newBook);
    }

    @PostMapping("/addBooks")
    public List<Book> addBooks(@Valid @RequestBody List<Book> books){
        return books.stream()
                .map(book ->{
                    Author author = authorService.getAuthorById(book.getAuthor().getAuthorId());
                    Category category = categoryService.getCategoryById(book.getCategory().getCategoryId());
                    Book newBook = new Book();
                    newBook.setAuthor(author);
                    newBook.setCategory(category);
                    newBook.setTitle(book.getTitle());
                    newBook.setBookId(book.getBookId());
                    return bookService.addBook(newBook);
                })
                .toList();
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/searchBookTitleLike")
    public List<Book> searchBookTitleLike(@Valid @RequestParam String title){
        return bookService.searchBookTitleLike(title);
    }

    @GetMapping("/searchBookTitleStartWith")
    public List<Book> searchBookTitleStartWith(@Valid @RequestParam String title){
        return bookService.searchBookTitleStartWith(title+'%');
    }

    @GetMapping("/searchBookEndsWith")
    public List<Book> searchBookEndsWith(@Valid @RequestParam String title){
        return bookService.searchBookEndsWith(title);
    }

    /* Queries */

    @GetMapping("/q1")
    public List<?> getBooksAuthorCategory(){
        return bookService.getBooksAuthorCategory();
    }

    @GetMapping("/q3")
    public List<?> getBooksCountInCategory(){
        return bookService.getBooksCountInCategory();
    }
}