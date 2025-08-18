package com.sparksupport.sparktraining.controllers.api;

import com.sparksupport.sparktraining.entity.Author;
import com.sparksupport.sparktraining.entity.Book;
import com.sparksupport.sparktraining.entity.Category;
import com.sparksupport.sparktraining.services.AuthorService;
import com.sparksupport.sparktraining.services.BookService;
import com.sparksupport.sparktraining.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final CategoryService categoryService;

    BookController(BookService bookService,
                   AuthorService authorService,
                   CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

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
    public List<Object[]> getBooksAuthorCategory() {
        return bookService.getBooksAuthorCategory();
    }

    @GetMapping("/q3")
    public List<Map<String, String>> getBooksCountInCategory() {
        return bookService.getBooksCountInCategory();
    }

    @GetMapping("/q6")
    public List<Map<String, String>> getBookAuthors() {
        return bookService.getBookAuthors();
    }

    @GetMapping("/q7")
    public List<Map<String, String>> getBooksNotBorrowed() {
        return bookService.getBooksNotBorrowed();
    }

    @GetMapping("/q12")
    public Page<Book> getBooksInPages(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        return bookService.getBooksInPages(page, size, sortBy);
    }

    @GetMapping("/q19")
    public List<Book> getBookNeverBorrowed(){
        return bookService.getBookNeverBorrowed();
    }
}