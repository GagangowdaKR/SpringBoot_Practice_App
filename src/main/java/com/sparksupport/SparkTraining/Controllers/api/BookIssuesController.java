package com.sparksupport.SparkTraining.Controllers.api;

import com.sparksupport.SparkTraining.Entity.Book;
import com.sparksupport.SparkTraining.Entity.BookIssues;
import com.sparksupport.SparkTraining.Entity.User;
import com.sparksupport.SparkTraining.Services.BookIssuesService;
import com.sparksupport.SparkTraining.Services.BookService;
import com.sparksupport.SparkTraining.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booksIssues")
public class BookIssuesController {

    @Autowired
    private BookIssuesService bookIssuesService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @PostMapping("/addBookIssue")
    public BookIssues addBookIssues(@Valid @RequestBody BookIssues bookIssues){
        Book book = bookService.getBookById(bookIssues.getBook().getBookId());
        User user = userService.getUserById(bookIssues.getUser().getUserId());
        BookIssues newIssues = new BookIssues();
        newIssues.setBook(book);
        newIssues.setUser(user);
        newIssues.setIssueId(bookIssues.getIssueId());
        newIssues.setIssueDate(bookIssues.getIssueDate());
        newIssues.setReturnDate(bookIssues.getReturnDate());
        return bookIssuesService.addBookIssues(bookIssues);
    }

    @PostMapping("/addBooksIssues")
    public List<BookIssues> addBooksIssues(@Valid @RequestBody List<BookIssues> booksIssues){
        return booksIssues.stream()
                .map((issueBook) -> {
                    Book book = bookService.getBookById(issueBook.getBook().getBookId());
                    User user = userService.getUserById(issueBook.getUser().getUserId());
                    BookIssues newIssues = new BookIssues();
                    newIssues.setBook(book);
                    newIssues.setUser(user);
                    newIssues.setIssueDate(issueBook.getIssueDate());
                    newIssues.setReturnDate(issueBook.getReturnDate());
                    newIssues.setIssueId(issueBook.getIssueId());
                    return bookIssuesService.addBookIssues(newIssues);
                }).collect(Collectors.toList());
    }

    @GetMapping("/getBooksIssues")
    public List<BookIssues> getBooksIssues(){
        return bookIssuesService.getBooksIssues();
    }

}
