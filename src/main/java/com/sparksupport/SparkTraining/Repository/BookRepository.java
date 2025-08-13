package com.sparksupport.SparkTraining.Repository;

import com.sparksupport.SparkTraining.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

//    @Query("SELECT b FROM BOOK b WHERE b.title LIKE '%The%'")
//    List<Book> searchBookLike(String name);
//
//    //    List all books with their author and category.
//    @Query("SELECT b.title, a.name, c.name FROM Book b JOIN b.author a JOIN b.category c")
//    List<Object[]> getBooksAuthorsCategory();

}