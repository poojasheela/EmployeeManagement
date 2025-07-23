package com.core.EmployeeManagement.test.controller;

import com.core.EmployeeManagement.entity.Department;

import com.core.EmployeeManagement.controller.DepartmentController;
import com.core.EmployeeManagement.dto.DepartmentDTO;
import com.core.EmployeeManagement.entity.Employee;
import com.core.EmployeeManagement.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController controller;

    @Mock
    private DepartmentServiceImpl service;

    private Department department;
    private DepartmentDTO dto;

    @BeforeEach
    void setup() {
        Employee employee = new Employee(50, "Roy", "roy@gmail.com", null);
        department = new Department(100L, "sales");
        dto = new DepartmentDTO();
        dto.setId(100L);
        dto.setDepartmentName("sales");
        dto.setEmployees(List.of(employee));
    }

    @Test
    void testCreateDepartmentWithEmployees() {
        when(service.createDepartmentWithEmployees("sales", dto.getEmployees())).thenReturn(department);

        ResponseEntity<Object> response = controller.createDepartmentWithEmployees(dto);

        assertEquals(200, response.getStatusCodeValue());
    }
}
