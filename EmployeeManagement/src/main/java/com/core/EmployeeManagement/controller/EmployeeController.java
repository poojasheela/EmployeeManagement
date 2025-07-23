package com.core.EmployeeManagement.controller;

import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> fetchAllEmployees() {
        List<EmployeeDTO> employees = service.fetchAllEmployees();
        log.info("Employees from service: " + employees);
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        EmployeeDTO createdEmployee = service.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/domain/qrs")
    public ResponseEntity<List<EmployeeDTO>> fetchEmployeesByEmailDomain() {
        List<EmployeeDTO> employees = service.fetchEmployeesByEmailDomain();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<EmployeeDTO>> fetchEmployeesByName(@PathVariable String name) {
        List<EmployeeDTO> employees = service.fetchEmployeesByName(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<EmployeeDTO>> fetchPaginatedEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<EmployeeDTO> employees = service.fetchPaginatedEmployees(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(employees);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = service.updateEmployeeById(id, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
