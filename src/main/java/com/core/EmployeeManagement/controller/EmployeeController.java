package com.core.EmployeeManagement.controller;

import com.core.EmployeeManagement.Employee;
import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

        private final EmployeeService service;

        public EmployeeController(EmployeeService service) {
            this.service = service;
        }

        @GetMapping("/get")
        public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
            List<EmployeeDTO> employees = service.getAllEmployees();
            System.out.println("Employees from service: " + employees);
            return ResponseEntity.ok(employees);
        }

        @GetMapping("/get/qrs")
        public ResponseEntity<List<EmployeeDTO>> getQrsEmployees() {
            List<EmployeeDTO> qrsEmployees = service.getEmployeesByQrsDomain();
            return ResponseEntity.ok(qrsEmployees);
        }

        @PostMapping("/post")
        public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<Object>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
            }
            EmployeeDTO employeeDTO = new EmployeeDTO(employee.getName(), employee.getEmail());
            return ResponseEntity.ok(service.createEmployee(employeeDTO));
        }
    @GetMapping("/get/domain/qrs")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDomain() {
        List<EmployeeDTO> employees = service.getEmployeesByDomain();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByName(@PathVariable String name) {
        List<EmployeeDTO> employees = service.getEmployeesByName(name);
        return ResponseEntity.ok(employees);
    }
    }
