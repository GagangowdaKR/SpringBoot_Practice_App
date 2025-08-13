package com.sparksupport.SparkTraining.Repository;

import com.sparksupport.SparkTraining.Entity.BookIssues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookIssuesRepository extends JpaRepository<BookIssues, Integer> {

}
