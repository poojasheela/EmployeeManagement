package com.core.EmployeeManagement;

import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.mapper.EmployeeMapper;
import com.core.EmployeeManagement.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class EmployeeServiceImplTest {

        @Mock
        private EmployeeMapper mapper;

        @InjectMocks
        private EmployeeServiceImpl employeeService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void getAllEmployees() {
            Employee employee = new Employee(1, "Alice", "alice@xyz.com", "pass1");
            EmployeeDTO employeeDTO = new EmployeeDTO("Alice", "alice@xyz.com");

            when(mapper.toDTOList(anyList())).thenReturn(List.of(employeeDTO));

            List<EmployeeDTO> employees = employeeService.getAllEmployees();

            assertNotNull(employees);
            assertEquals(1, employees.size());
            assertEquals("Alice", employees.getFirst().getFullName());
        }

        @Test
        void getEmployeesByQrsDomain() {
            Employee employee = new Employee(2, "Bob", "bob@qrs.com", "pass2");
            EmployeeDTO employeeDTO = new EmployeeDTO("Bob", "bob@qrs.com");

            when(mapper.toDTOList(anyList())).thenReturn(List.of(employeeDTO));

            List<EmployeeDTO> employees = employeeService.getEmployeesByQrsDomain();

            assertNotNull(employees);
            assertEquals(1, employees.size());
            assertEquals("Bob", employees.getFirst().getFullName());
        }

        @Test
        void createEmployee() {
            EmployeeDTO newEmployeeDTO = new EmployeeDTO("David", "david@xyz.com");
            Employee createdEmployee = new Employee(5, "David", "david@xyz.com", "pass5");
            EmployeeDTO createdEmployeeDTO = new EmployeeDTO("David", "david@xyz.com");

            when(mapper.toEntity(newEmployeeDTO)).thenReturn(createdEmployee);
            when(mapper.toDTO(createdEmployee)).thenReturn(createdEmployeeDTO);

            EmployeeDTO result = employeeService.createEmployee(newEmployeeDTO);

            assertNotNull(result);
            assertEquals("David", result.getFullName());
            assertEquals("david@xyz.com", result.getContactEmail());
        }
    }
