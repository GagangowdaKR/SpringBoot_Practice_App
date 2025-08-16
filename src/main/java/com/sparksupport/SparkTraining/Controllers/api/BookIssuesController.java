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

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booksIssues")
public class BookIssuesController {

    private final BookIssuesService bookIssuesService;
    private final BookService bookService;
    private final UserService userService;

    BookIssuesController(BookIssuesService bookIssuesService, BookService bookService, UserService userService){
        this.bookIssuesService = bookIssuesService;
        this.bookService = bookService;
        this.userService = userService;
    }

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

    @GetMapping("/q2")
    public List<Map<String,String>> getBorrowedBooksAndMembers(){
        return bookIssuesService.getBorrowedBooksAndMembers();
    }

    @GetMapping("/q4")
    public List<Map<String,String>> getBooksOnLoan(){
        return bookIssuesService.getBooksOnLoad();
    }

    @GetMapping("/q5")
    public List<Map<String,String>> getMembersBorrowedAfterDate(@Valid @RequestParam LocalDate date){
        return bookIssuesService.getMembersBorrowedAfterDate(date);
    }

    @GetMapping("/q8")
    public List<?> getCountBooksBorrowedEachMember(){
        return bookIssuesService.getCountBooksBorrowedEachMember();
    }

    @GetMapping("/q9")
    public List<?> getMostBorrowedBook(){
        return bookIssuesService.getMostBorrowedBook();
    }

    @GetMapping("/q10")
    public List<?> getLoanBookDetails(){
        return bookIssuesService.getLoanBookDetails();
    }
}
