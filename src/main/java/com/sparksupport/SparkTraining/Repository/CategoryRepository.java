package com.sparksupport.SparkTraining.Repository;

import com.sparksupport.SparkTraining.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
