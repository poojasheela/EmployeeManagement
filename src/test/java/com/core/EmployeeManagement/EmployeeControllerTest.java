package com.core.EmployeeManagement;

import com.core.EmployeeManagement.Employee;
import com.core.EmployeeManagement.controller.EmployeeController;
import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    @Mock
    private BindingResult bindingResult;

    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {

        employee = new Employee(1, "Alice", "alice@qrs.com", "pass123");
        employeeDTO = new EmployeeDTO("Alice", "alice@qrs.com");


        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllEmployees_ReturnsEmployeeDTOList() {

        when(service.getAllEmployees()).thenReturn(List.of(employeeDTO));

        ResponseEntity<List<EmployeeDTO>> response = controller.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(1, response.getBody().size());
        assertEquals("Alice", response.getBody().getFirst().getFullName());
    }


    @Test
    public void getQrsEmployees_ReturnsFilteredEmployeeDTOList() {

        when(service.getEmployeesByQrsDomain()).thenReturn(List.of(employeeDTO));

        ResponseEntity<List<EmployeeDTO>> response = controller.getQrsEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("alice@qrs.com", response.getBody().getFirst().getContactEmail());
    }


  @Test
  public void createEmployee() {

          when(bindingResult.hasErrors()).thenReturn(false);
          when(service.createEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO);

          ResponseEntity<Object> response = controller.createEmployee(employee, bindingResult);

          assertEquals(HttpStatus.OK, response.getStatusCode());

          when(bindingResult.hasErrors()).thenReturn(true);
          when(bindingResult.getFieldErrors()).thenReturn(
                  List.of(new FieldError("employee", "email", "Invalid email format"))
          );
          employee.setEmail("invalid-email");

                  response = controller.createEmployee(employee, bindingResult);

          assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

      }
  }
