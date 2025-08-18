package com.sparksupport.sparktraining.repository;

import com.sparksupport.sparktraining.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    List each member’s most recently borrowed book.
    @Query(value = "SELECT u.name, b.title, bi.issue_date " +
            "FROM user u " +
            "JOIN Book_Issues bi ON u.user_id = bi.user_id " +
            "JOIN Book b ON bi.book_id = b.book_id " +
            "WHERE bi.issue_date = ( SELECT issue_date " +
            "FROM book_issues " +
            "WHERE user_id = bi.user_id " +
            "ORDER BY issue_date DESC " +
            "LIMIT 1)", nativeQuery = true)
    List<Object[]> findLatestBorrowedBook();
}
