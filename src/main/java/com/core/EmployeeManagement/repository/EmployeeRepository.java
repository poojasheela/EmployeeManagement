package com.core.EmployeeManagement.repository;

import com.core.EmployeeManagement.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.core.EmployeeManagement.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

        @Query(value = "SELECT * FROM employee WHERE email LIKE  '%@qrs.com'", nativeQuery = true)
        List<Employee> findEmployeesByEmailDomain(String domain);


        @Query(value = "SELECT * FROM employee WHERE name = :name", nativeQuery = true)
        List<Employee> findEmployeesByName(String name);

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    Page<Employee> findAll(Pageable pageable);

    }

