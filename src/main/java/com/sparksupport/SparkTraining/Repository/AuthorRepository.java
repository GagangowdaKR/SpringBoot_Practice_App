package com.sparksupport.SparkTraining.Repository;

import com.sparksupport.SparkTraining.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {



}
