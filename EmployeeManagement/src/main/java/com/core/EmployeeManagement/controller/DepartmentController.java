package com.core.EmployeeManagement.controller;
import com.core.EmployeeManagement.entity.Department;
import com.core.EmployeeManagement.dto.DepartmentDTO;
import com.core.EmployeeManagement.repository.DepartmentRepository;
import com.core.EmployeeManagement.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    DepartmentServiceImpl departmentService;

    @PostMapping("/create")
    public ResponseEntity<Object> createDepartmentWithEmployees(@RequestBody DepartmentDTO departmentDTO) {
        try {
            Department department = departmentService.createDepartmentWithEmployees(departmentDTO.getId(),departmentDTO.getDepartmentName(), departmentDTO.getEmployees());
            return ResponseEntity.ok(department);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error creating department with employees: " + e.getMessage());
        }
    }
}


