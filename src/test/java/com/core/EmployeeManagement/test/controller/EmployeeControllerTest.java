package com.core.EmployeeManagement.test.controller;
import com.core.EmployeeManagement.controller.EmployeeController;
import com.core.EmployeeManagement.entity.Department;
import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.entity.Employee;
import com.core.EmployeeManagement.mapper.EmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import com.core.EmployeeManagement.repository.EmployeeRepository;
import com.core.EmployeeManagement.service.impl.EmployeeServiceImpl;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeServiceImpl service;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper mapper;

    @Mock
    private BindingResult bindingResult;

    private EmployeeDTO dto;
    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dto = new EmployeeDTO("Roy", "roy@gmail.com");
        Department department = new Department(100L, "Sales");
        employee = new Employee(50, "Roy", "roy@gmail.com", department);
    }

    @Test
    void testCreateEmployee() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(service.createEmployee(dto)).thenReturn(dto);

        ResponseEntity<EmployeeDTO> response = controller.createEmployee(dto, bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Roy", response.getBody().getFullName());
    }

    @Test
    void testCreateEmployeeWithValidationError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<EmployeeDTO> response = controller.createEmployee(dto, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testFetchAllEmployees() {
        List<EmployeeDTO> list = List.of(dto);
        when(service.fetchAllEmployees()).thenReturn(list);

        ResponseEntity<List<EmployeeDTO>> response = controller.fetchAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Roy", response.getBody().get(0).getFullName());
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(service).deleteEmployeeById(50);

        ResponseEntity<String> response = controller.deleteEmployee(50);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee deleted successfully", response.getBody());
    }

    @Test
    void testUpdateEmployee() {
        EmployeeDTO updatedDTO = new EmployeeDTO("Updated Roy", "updated@gmail.com");
        when(service.updateEmployeeById(50, updatedDTO)).thenReturn(updatedDTO);

        ResponseEntity<EmployeeDTO> response = controller.updateEmployee(50, updatedDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Roy", response.getBody().getFullName());
    }

    @Test
    void testFetchEmployeesByName() {
        List<EmployeeDTO> employees = List.of(dto);
        when(service.fetchEmployeesByName("Roy")).thenReturn(employees);

        ResponseEntity<List<EmployeeDTO>> response = controller.fetchEmployeesByName("Roy");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Roy", response.getBody().get(0).getFullName());
    }

    @Test
    void testFetchEmployeesByEmailDomain() {
        List<EmployeeDTO> employees = List.of(dto);
        when(service.fetchEmployeesByEmailDomain()).thenReturn(employees);

        ResponseEntity<List<EmployeeDTO>> response = controller.fetchEmployeesByEmailDomain();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Roy", response.getBody().get(0).getFullName());
    }


}
