package com.core.EmployeeManagement.service;

import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
        EmployeeDTO createEmployee(EmployeeDTO dto);
        List<EmployeeDTO> fetchAllEmployees();
        List<EmployeeDTO> fetchEmployeesByEmailDomain();
        List<EmployeeDTO> fetchEmployeesByName(String name);
        Page<EmployeeDTO> fetchPaginatedEmployees(int page, int size, String sortBy, String sortDirection);
        EmployeeDTO updateEmployeeById(int id, EmployeeDTO dto);
        void deleteEmployeeById(int id);
    }
