package com.sparksupport.SparkTraining.Repository;

import com.sparksupport.SparkTraining.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
