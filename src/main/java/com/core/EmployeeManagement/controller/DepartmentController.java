package com.core.EmployeeManagement.controller;
import com.core.EmployeeManagement.Department;
import com.core.EmployeeManagement.Employee;
import com.core.EmployeeManagement.dto.DepartmentDTO;
import com.core.EmployeeManagement.service.DepartmentService;
import com.core.EmployeeManagement.service.DepartmentServiceImpl;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createDepartmentWithEmployees(@RequestBody DepartmentDTO departmentDTO) {

        try {

            Department department = departmentService.createDepartmentWithEmployees(departmentDTO.getDepartmentName(), departmentDTO.getEmployees());
            return ResponseEntity.ok(department);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error creating department with employees: " + e.getMessage());
        }
    }
}


