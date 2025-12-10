package com.nick.spring.repositories;

import com.nick.spring.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    Optional<EmployeeEntity> findByEmail(String email);

    @Query(value = "Select MAX(Salary)\n" +
            "From employees",  nativeQuery = true)
    Optional<Double> findMaximumSalary();
}
