package com.sparksupport.SparkTraining.Services;

import com.sparksupport.SparkTraining.Entity.Book;
import com.sparksupport.SparkTraining.Entity.BookIssues;
import com.sparksupport.SparkTraining.Repository.BookIssuesRepository;
import jakarta.validation.Valid;
import org.hibernate.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Map<String,String>> getBorrowedBooksAndMembers() {
        // Converting List of Object[] into List of Map<> by defning the key and value

        return bookIssuesRepository.findAllBorrowedBookAndMembers().stream()
                .map((record) ->{
                    Map<String,String> hm = new HashMap<>();
                    hm.put("BookTitle", String.valueOf(record[0]));
                    hm.put("MemberName", String.valueOf(record[1]));
                    hm.put("BorrowDate", String.valueOf(record[2]));
                    return hm;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, String>> getBooksOnLoad() {
        return bookIssuesRepository.findLoanBooks().stream()
                .map((data) ->{
                    Map<String,String> map = new HashMap<>();
                    map.put("BookTitle", String.valueOf(data[0]));
                    map.put("IssueDate", String.valueOf(data[1]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, String>> getMembersBorrowedAfterDate(LocalDate date) {
        return bookIssuesRepository.findMemberBorrowedAfterDate(date).stream()
                .map(object ->{
                    Map<String,String> map = new HashMap<>();
                    map.put("Member",String.valueOf(object[0]));
                    map.put("BookTitle", String.valueOf(object[1]));
                    map.put("BorrowDate", String.valueOf(object[2]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<?> getCountBooksBorrowedEachMember(){
        return bookIssuesRepository.findMemberBooksCount().stream()
                .map(anyType ->{
                    Object[] object = (Object[]) anyType;
                    Map<String,String> map = new HashMap<>();
                    map.put("User Name", String.valueOf(object[0]));
                    map.put("Total Books", String.valueOf(object[1]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<?> getMostBorrowedBook(){
        return bookIssuesRepository.findMostBorrowedBook().stream()
                .map(anyType ->{
                    Object[] object = (Object[]) anyType;
                    Map<String, String> map = new HashMap<>();
                    map.put("Book Id",String.valueOf(object[0]));
                    map.put("Book Title", String.valueOf(object[1]));
                    map.put("Total Borrowed", String.valueOf(object[2]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<?> getLoanBookDetails(){
        return bookIssuesRepository.findLoanBooksDetails().stream()
                .map(anyType ->{
                    Object[] object = (Object[]) anyType;
                    LinkedHashMap<String, String> map = new LinkedHashMap<>();
                    map.put("Book Title", String.valueOf(object[0]));
                    map.put("Member Name", String.valueOf(object[1]));
                    map.put("Borrowed Date", String.valueOf(object[2]));
                    map.put("Return Date", String.valueOf(object[3]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> getLatestIssuedBookUsingPagination(int page, int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<?> latestBook = bookIssuesRepository.findLatestBookByPaging(pageRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("Books", latestBook.getContent());

//        response.put("Current Page", latestBook.getNumber());
//        response.put("Total Books", latestBook.getTotalElements());
//        response.put("Total Pages", latestBook.getTotalPages());
        return ResponseEntity.ok().body(response);
    }

    public List<?> getAuthorMembers(){
        return bookIssuesRepository.findAuthorMember();
    }

    public List<?> getMemberBorrowedMoreThanAvg() {
        return bookIssuesRepository.findMemberBorrowedMoreThanAvg();
    }

    public List<?> getMostBorrowed(int val) {
        return bookIssuesRepository.findMostBorrowed(val).stream()
                .map(anyType ->{
                    Object[] object = (Object[]) anyType;
                    Map<String,String> map = new HashMap<>();
                    map.put("Book Title", String.valueOf(object[0]));
                    map.put("Total Borrow", String.valueOf(object[1]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<?> getMemberBorrowedMultiCategory() {
        return bookIssuesRepository.findMemberBorrowedMultiCategory().stream()
                .map(anyType ->{
                    Object[] object = (Object[]) anyType;
                    Map<String,String> map = new HashMap<>();
                    map.put("Member Name", String.valueOf(object[0]));
                    map.put("Borrow Category Count", String.valueOf(object[1]));
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<?> getBorrowedReturnedWithinDays(int days) {
        return bookIssuesRepository.findBorrowedReturnedWithinDays(days).stream()
                .map(anyType -> {
                    Object[] object = (Object[]) anyType;
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