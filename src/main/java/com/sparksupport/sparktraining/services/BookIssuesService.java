package com.sparksupport.sparktraining.services;

import com.sparksupport.sparktraining.entity.BookIssues;
import com.sparksupport.sparktraining.repository.BookIssuesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookIssuesService {

    private final BookIssuesRepository bookIssuesRepository;

    BookIssuesService(BookIssuesRepository bookIssuesRepository) {
        this.bookIssuesRepository = bookIssuesRepository;
    }

    public BookIssues addBookIssues(BookIssues bookIssues){
        return bookIssuesRepository.save(bookIssues);
    }

    public List<BookIssues> getBooksIssues(){
        return bookIssuesRepository.findAll();
    }

    public List<Map<String,String>> getBorrowedBooksAndMembers() {
        // Converting List of Object[] into List of Map<> by defining the key and value

        return bookIssuesRepository.findAllBorrowedBookAndMembers().stream()
                .map(object -> {
                    Map<String,String> hm = new HashMap<>();
                    hm.put("Book Name", String.valueOf(object[0]));
                    hm.put("MemberName", String.valueOf(object[1]));
                    hm.put("BorrowDate", String.valueOf(object[2]));
                    return hm;
                })
                .toList();
    }

    public List<Map<String, String>> getBooksOnLoad() {
        return bookIssuesRepository.findLoanBooks().stream()
                .map(data -> {
                    Map<String,String> map = new HashMap<>();
                    map.put("BookTitle", String.valueOf(data[0]));
                    map.put("IssueDate", String.valueOf(data[1]));
                    return map;
                })
                .toList();
    }

    public List<Map<String, String>> getMembersBorrowedAfterDate(LocalDate date) {
        return bookIssuesRepository.findMemberBorrowedAfterDate(date).stream()
                .map(object ->{
                    Map<String,String> map = new HashMap<>();
                    map.put("Member",String.valueOf(object[0]));
                    map.put("Book-Title", String.valueOf(object[1]));
                    map.put("BorrowDate", String.valueOf(object[2]));
                    return map;
                })
                .toList();
    }

    public List<Map<String, String>> getCountBooksBorrowedEachMember() {
        return bookIssuesRepository.findMemberBooksCount().stream()
                .map(object -> {
                    Map<String,String> map = new HashMap<>();
                    map.put("User Name", String.valueOf(object[0]));
                    map.put("Total Books", String.valueOf(object[1]));
                    return map;
                })
                .toList();
    }

    public List<Map<String, String>> getMostBorrowedBook() {
        return bookIssuesRepository.findMostBorrowedBook().stream()
                .map(object -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("Book Id",String.valueOf(object[0]));
                    map.put("TitleOfBook", String.valueOf(object[1]));
                    map.put("Total Borrowed", String.valueOf(object[2]));
                    return map;
                })
                .toList();
    }

    public List<Map<String, String>> getLoanBookDetails() {
        return bookIssuesRepository.findLoanBooksDetails().stream()
                .map(object -> {
                    LinkedHashMap<String, String> map = new LinkedHashMap<>();
                    map.put("BookTitle", String.valueOf(object[0]));
                    map.put("Member Name", String.valueOf(object[1]));
                    map.put("Borrowed Date", String.valueOf(object[2]));
                    map.put("Return Date", String.valueOf(object[3]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public ResponseEntity<Map<String, Object>> getLatestIssuedBookUsingPagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<?> latestBook = bookIssuesRepository.findLatestBookByPaging(pageRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("Books", latestBook.getContent());
        return ResponseEntity.ok(response);
    }


    public List<String> getAuthorMembers() {
        return bookIssuesRepository.findAuthorMember();
    }

    public List<Object[]> getMemberBorrowedMoreThanAvg() {
        return bookIssuesRepository.findMemberBorrowedMoreThanAvg();
    }

    public List<Map<String, String>> getMostBorrowed(int val) {
        return bookIssuesRepository.findMostBorrowed(val).stream()
                .map(object -> {
                    Map<String,String> map = new HashMap<>();
                    map.put("Book Title", String.valueOf(object[0]));
                    map.put("Total Borrow", String.valueOf(object[1]));
                    return map;
                })
                .toList();
    }

    public List<Map<String, String>> getMemberBorrowedMultiCategory() {
        return bookIssuesRepository.findMemberBorrowedMultiCategory().stream()
                .map(object -> {
                    Map<String,String> map = new HashMap<>();
                    map.put("Member Name", String.valueOf(object[0]));
                    map.put("Borrow Category Count", String.valueOf(object[1]));
                    return map;
                })
                .toList();
    }

    public List<Map<String, String>> getBorrowedReturnedWithinDays(int days) {
        return bookIssuesRepository.findBorrowedReturnedWithinDays(days).stream()
                .map(object -> {
                    LinkedHashMap<String,String> map = new LinkedHashMap<>();
                    map.put("Book Id",String.valueOf(object[0]));
                    map.put("Book Title", String.valueOf(object[1]));
                    map.put("Issue Date",String.valueOf(object[2]));
                    map.put("Return Date", String.valueOf(object[3]));
                    return map;
                })
                .collect(Collectors.toList());
    }

}