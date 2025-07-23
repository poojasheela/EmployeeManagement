package com.core.EmployeeManagement.service;

import com.core.EmployeeManagement.entity.Department;

import com.core.EmployeeManagement.entity.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DepartmentService {
    @Transactional
    Department createDepartmentWithEmployees(Long id,String departmentName, List<Employee> employees);
}


