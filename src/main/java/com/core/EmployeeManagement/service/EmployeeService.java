package com.core.EmployeeManagement.service;

import com.core.EmployeeManagement.Employee;
import com.core.EmployeeManagement.dto.EmployeeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    List<EmployeeDTO> getEmployeesByQrsDomain();
    EmployeeDTO createEmployee(EmployeeDTO dto);

    List<EmployeeDTO> getEmployeesByDomain();
    List<EmployeeDTO> getEmployeesByName(String name);
    Page<Employee> getAll(int page, int size, String sortBy, String sortDirection);
}