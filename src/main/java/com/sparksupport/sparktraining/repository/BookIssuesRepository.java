package com.sparksupport.sparktraining.repository;

import com.sparksupport.sparktraining.entity.BookIssues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    List<Object[]> findMemberBooksCount();

    /*    Find the most borrowed book using a subquery.
        @Query("Select b.bookId, b.title, count(*) " +
                "From BookIssues bi " +
                "JOIN bi.book b " +
                "GROUP BY bi.book " +
                "ORDER BY count(*) desc " +
                "LIMIT 1")
        List<?> findMostBorrowedBook();

        OR
*/
    @Query(value = """
            SELECT bi.book_id, (SELECT b.title FROM book b WHERE b.book_id = bi.book_id), COUNT(*) AS total
            FROM book_issues bi
            GROUP BY bi.book_id
            ORDER BY total desc
            LIMIT 1""", nativeQuery = true)
    List<Object[]> findMostBorrowedBook();

//    Create a view for current loans with book title, member name, loan date.
    @Query("Select b.title, u.name, bi.issueDate, bi.returnDate " +
            "FROM BookIssues bi " +
            "JOIN bi.book b JOIN bi.user u " +
            "WHERE bi.returnDate is null")
    List<Object[]> findLoanBooksDetails();

//  12. Display the latest 5 books.
    @Query("SELECT b.id, b.title, bi.issueDate " +
            "FROM BookIssues bi " +
            "JOIN bi.book b " +
            "ORDER BY bi.issueDate DESC")
    Page<Object[]> findLatestBookByPaging(PageRequest page);

//    Use UNION to combine author and member names
    @Query(value="SELECT name FROM author " +
            "UNION " +
            "SELECT name FROM user", nativeQuery = true)
    List<String> findAuthorMember();


//    Find members who borrowed more books than average (advanced subquery)
    @Query(value = "Select u.name, count(*) as Total_Books " +
            "FROM User u " +
            "JOIN book_issues bi " +
            "ON u.user_id = bi.user_id " +
            "GROUP BY bi.user_id " +
            "HAVING COUNT(*) > ( " +
            "SELECT AVG(TotalBook) " +
            "FROM (Select count(*) AS TotalBook FROM Book_issues GROUP BY user_id) " +
            "AS dummy )", nativeQuery = true)
    List<Object[]> findMemberBorrowedMoreThanAvg();

//    Find the top 3 most borrowed books.
    @Query("SELECT b.title, COUNT(*) " +
            "FROM BookIssues bi " +
            "JOIN bi.book b " +
            "GROUP BY bi.book " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT :val")
    List<Object[]> findMostBorrowed(@Param("val") int val);

//    List members who have borrowed books from more than one category.
    @Query("SELECT u.name, COUNT(b.category) " +
            "FROM BookIssues bi " +
            "JOIN bi.book b " +
            "JOIN bi.user u " +
            "GROUP BY bi.user " +
            "HAVING COUNT(b.category) > 1")
    List<Object[]> findMemberBorrowedMultiCategory();

//    Find books that were borrowed and returned within 7 days.
    @Query("SELECT b.bookId, b.title, bi.issueDate, bi.returnDate " +
            "FROM BookIssues bi " +
            "JOIN bi.book b " +
            "WHERE DATEDIFF(bi.returnDate, bi.issueDate) < :day")
    List<Object[]> findBorrowedReturnedWithinDays(@Param("day") int day);

}
