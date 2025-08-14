package com.sparksupport.SparkTraining.Repository;

import com.sparksupport.SparkTraining.Entity.BookIssues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookIssuesRepository extends JpaRepository<BookIssues, Integer> {

//    Show members and the books they’ve borrowed.
    @Query("select b.title, u.name, bi.issueDate from BookIssues bi join bi.book b join bi.user u")
    List<Object[]> findAllBorrowedBookAndMembers();

//    List books currently on loan (return_date IS NULL).
    @Query("select b.title, bi.issueDate " +
            "from BookIssues bi " +
            "join bi.book b " +
            "where bi.returnDate is null")
    List<Object[]> findLoanBooks();

//    Find members who borrowed books after a specific date.
    @Query("SELECT u.name, b.title, bi.issueDate " +
            "FROM BookIssues bi " +
            "JOIN bi.user u JOIN bi.book b " +
            "WHERE issueDate > :date")
    List<Object[]> findMemberBorrowedAfterDate(@Param("date") LocalDate date);
}