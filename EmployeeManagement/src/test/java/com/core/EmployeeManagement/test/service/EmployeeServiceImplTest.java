package com.core.EmployeeManagement.test.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.core.EmployeeManagement.entity.Department;

import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.entity.Employee;
import com.core.EmployeeManagement.mapper.EmployeeMapper;
import com.core.EmployeeManagement.repository.DepartmentRepository;
import com.core.EmployeeManagement.repository.EmployeeRepository;
import com.core.EmployeeManagement.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl service;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeMapper mapper;

    private Employee testEmployee;
    private EmployeeDTO testDTO;
    private Department testDepartment;

    @BeforeEach
    void setUp() {
        testDepartment = new Department(100L, "sales");
        testEmployee = new Employee(50, "Roy", "roy@gmail.com", "roy123", testDepartment);
        testDTO = new EmployeeDTO("Roy", "roy@gmail.com");
    }

    @Test
    void testFetchAllEmployees() {
        List<Employee> employees = List.of(testEmployee);
        List<EmployeeDTO> dtoList = List.of(testDTO);

        when(employeeRepository.findAll()).thenReturn(employees);
        when(mapper.toDTOList(employees)).thenReturn(dtoList);

        List<EmployeeDTO> result = service.fetchAllEmployees();

        assertEquals(1, result.size());
        assertEquals("Roy", result.get(0).getFullName());
    }

    @Test
    void testCreateEmployee() {
        when(mapper.toEntity(testDTO)).thenReturn(testEmployee);
        when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);
        when(mapper.toDTO(testEmployee)).thenReturn(testDTO);

        EmployeeDTO result = service.createEmployee(testDTO);

        assertEquals("Roy", result.getFullName());
        verify(employeeRepository, times(1)).save(testEmployee);
    }

    @Test
    void testFetchEmployeesByEmailDomain() {
        when(employeeRepository.findEmployeesByEmailDomain("@qrs.com")).thenReturn(List.of(testEmployee));
        when(mapper.toDTOList(List.of(testEmployee))).thenReturn(List.of(testDTO));

        List<EmployeeDTO> result = service.fetchEmployeesByEmailDomain();

        assertEquals(1, result.size());
        assertEquals("Roy", result.get(0).getFullName());
    }

    @Test
    void testUpdateEmployeeById() {
        EmployeeDTO updatedDTO = new EmployeeDTO("Updated Roy", "updated@gmail.com");
        Employee updatedEmployee = new Employee(50, "Updated Roy", "updated@gmail.com", "roy123", testDepartment);

        when(employeeRepository.findById(50)).thenReturn(Optional.of(testEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);
        when(mapper.toDTO(updatedEmployee)).thenReturn(updatedDTO);

        EmployeeDTO result = service.updateEmployeeById(50, updatedDTO);

        assertEquals("Updated Roy", result.getFullName());
    }

    @Test
    void testDeleteEmployeeById() {
        when(employeeRepository.existsById(50)).thenReturn(true);

        service.deleteEmployeeById(50);

        verify(employeeRepository).deleteById(50);
    }

    @Test
    void testDeleteEmployeeById_NotFound() {
        when(employeeRepository.existsById(999)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> service.deleteEmployeeById(999));
        assertEquals("Employee not found", exception.getMessage());
    }
}
