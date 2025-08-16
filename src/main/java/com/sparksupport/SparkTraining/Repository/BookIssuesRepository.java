package com.sparksupport.SparkTraining.Repository;

import com.sparksupport.SparkTraining.Entity.BookIssues;
import org.hibernate.mapping.Value;
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

//    Count how many books are borrowed by each member.
    @Query("Select u.name, count(*) from BookIssues bi JOIN bi.user u GROUP BY bi.user")
    List<?> findMemberBooksCount();

//    Find the most borrowed book using a subquery.
/*
    @Query("Select b.bookId, b.title, count(*) " +
            "From BookIssues bi " +
            "JOIN bi.book b " +
            "GROUP BY bi.book " +
            "ORDER BY count(*) desc " +
            "LIMIT 1")
    List<?> findMostBorrowedBook();

    OR

 */
    @Query(value = "SELECT bi.book_id, (SELECT b.title FROM book b WHERE b.book_id = bi.book_id), COUNT(*) AS total " +
            "FROM book_issues bi " +
            "GROUP BY bi.book_id " +
            "ORDER BY total desc " +
            "LIMIT 1", nativeQuery = true)
    List<?> findMostBorrowedBook();

//    Create a view for current loans with book title, member name, loan date.
    @Query("Select b.title, u.name, bi.issueDate, bi.returnDate " +
            "FROM BookIssues bi " +
            "JOIN bi.book b JOIN bi.user u " +
            "WHERE bi.returnDate is null")
    List<?> findLoanBooksDetails();
}