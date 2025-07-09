package com.core.EmployeeManagement.repository;

import com.core.EmployeeManagement.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository< Employee,Integer> {
    @Query(value = "SELECT * FROM employee WHERE email LIKE  '%@qrs.com'", nativeQuery = true)
    List<Employee> findEmployeesByEmailDomain(String domain);


    @Query(value = "SELECT * FROM employee WHERE name = :name", nativeQuery = true)
    List<Employee> findEmployeesByName(String name);
}
