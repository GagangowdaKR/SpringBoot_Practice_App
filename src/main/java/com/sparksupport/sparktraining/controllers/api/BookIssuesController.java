package com.sparksupport.sparktraining.controllers.api;

import com.sparksupport.sparktraining.entity.Book;
import com.sparksupport.sparktraining.entity.BookIssues;
import com.sparksupport.sparktraining.entity.User;
import com.sparksupport.sparktraining.services.BookIssuesService;
import com.sparksupport.sparktraining.services.BookService;
import com.sparksupport.sparktraining.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
        User user = userService.getUserById(bookIssues.getUser().getUserId()).getBody();
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
                .map(issueBook -> {
                    Book book = bookService.getBookById(issueBook.getBook().getBookId());
                    User user = userService.getUserById(issueBook.getUser().getUserId()).getBody();
                    BookIssues newIssues = new BookIssues();
                    newIssues.setBook(book);
                    newIssues.setUser(user);
                    newIssues.setIssueDate(issueBook.getIssueDate());
                    newIssues.setReturnDate(issueBook.getReturnDate());
                    newIssues.setIssueId(issueBook.getIssueId());
                    return bookIssuesService.addBookIssues(newIssues);
                }).toList();
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
    public List<Map<String, String>> getCountBooksBorrowedEachMember() {
        return bookIssuesService.getCountBooksBorrowedEachMember();
    }

    @GetMapping("/q9")
    public List<Map<String, String>> getMostBorrowedBook() {
        return bookIssuesService.getMostBorrowedBook();
    }

    @GetMapping("/q10")
    public List<Map<String, String>> getLoanBookDetails() {
        return bookIssuesService.getLoanBookDetails();
    }

    @GetMapping("/q11")
    public ResponseEntity<Map<String, Object>> getLatestIssuedBookUsingPagination(int page, int size) {
        return bookIssuesService.getLatestIssuedBookUsingPagination(page,size);
    }

    @GetMapping("/q14")
    public List<String> getAuthorMembers() {
        return bookIssuesService.getAuthorMembers();
    }

    @GetMapping("/q15")
    public List<Object[]> getMemberBorrowedMoreThanAvg() {
        return bookIssuesService.getMemberBorrowedMoreThanAvg();
    }

    @GetMapping("/q16/{val}")
    public List<Map<String, String>> getMostBorrowed(@PathVariable int val) {
        return bookIssuesService.getMostBorrowed(val);
    }

    @GetMapping("/q17")
    public List<Map<String, String>> getMemberBorrowedMultiCategory() {
        return bookIssuesService.getMemberBorrowedMultiCategory();
    }

    @GetMapping("/q18/{day}")
    public List<Map<String, String>> getBorrowedReturnedWithinDays(@PathVariable int day) {
        return bookIssuesService.getBorrowedReturnedWithinDays(day);
    }


}
