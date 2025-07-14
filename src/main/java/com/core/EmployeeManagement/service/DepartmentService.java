package com.core.EmployeeManagement.service;

import com.core.EmployeeManagement.Department;
import com.core.EmployeeManagement.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DepartmentService {


    @Transactional
    Department createDepartmentWithEmployees(String departmentName, List<Employee> employees);
}


