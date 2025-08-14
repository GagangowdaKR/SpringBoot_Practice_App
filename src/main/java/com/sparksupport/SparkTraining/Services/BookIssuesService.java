package com.sparksupport.SparkTraining.Services;

import com.sparksupport.SparkTraining.Entity.Book;
import com.sparksupport.SparkTraining.Entity.BookIssues;
import com.sparksupport.SparkTraining.Repository.BookIssuesRepository;
import jakarta.validation.Valid;
import org.hibernate.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
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
}
