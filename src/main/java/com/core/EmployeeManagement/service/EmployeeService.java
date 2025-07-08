package com.core.EmployeeManagement.service;

import com.core.EmployeeManagement.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
        List<EmployeeDTO> getAllEmployees();
        List<EmployeeDTO> getEmployeesByQrsDomain();
        EmployeeDTO createEmployee(EmployeeDTO dto);
    }


