package com.sparksupport.SparkTraining.Repository;

import com.sparksupport.SparkTraining.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query("SELECT b FROM Book b WHERE b.title LIKE :title")
    List<Book> findByTitleLike(@Param("title") String title);
//    OR
    List<Book> findByTitleContaining(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.title LIKE :title")
    List<Book> findByTitleStartWith(@Param("title") String title);
//    OR
    List<Book> findByTitleStartingWith(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.title like :title")
    List<Book> findByTitleEndsWith(@Param("title") String title);
//  OR
    List<Book> findByTitleEndingWith(String str);


/* ****** QUERIES ****** */


//        List all books with their author and category.
    @Query("SELECT b.title, a.name, c.name FROM Book b JOIN b.author a JOIN b.category c")
    List<?> findAllBooksAuthorCategory();


//    Count how many books are available per category.
    @Query("Select c.name, count(*) from Book b join b.category c group by c")
    List<Object[]> countBooksInCategory();

//    Use INNER JOIN to show book titles with author names.
    @Query("SELECT b.title, a.name FROM Book b INNER JOIN b.author a")
    List<?> findBookAuthors();

//    Use a subquery to list books that are not currently borrowed.
    @Query(value = "Select b.book_id, b.title from Book b where book_id not in (select book_id from Book_Issues)", nativeQuery = true)
    List<?> findBookNotBorrowed();

}