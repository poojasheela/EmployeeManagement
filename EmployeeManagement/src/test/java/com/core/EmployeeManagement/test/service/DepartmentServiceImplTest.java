package com.core.EmployeeManagement.test.service;
import com.core.EmployeeManagement.entity.Department;
import com.core.EmployeeManagement.entity.Employee;
import com.core.EmployeeManagement.repository.DepartmentRepository;
import com.core.EmployeeManagement.repository.EmployeeRepository;
import com.core.EmployeeManagement.service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl service;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    private Department department;
    private Employee employee;

    @BeforeEach
    void setup() {
        department = new Department(100L, "sales");
        employee = new Employee(50, "Roy", "roy@gmail.com", "roy123", null);
    }

    @Test
    void testCreateDepartmentWithEmployees() {
        List<Employee> employees = List.of(employee);

        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        when(employeeRepository.saveAll(employees)).thenReturn(employees);

        Department result = service.createDepartmentWithEmployees(100L, "sales", employees);

        assertEquals("sales", result.getName());
        verify(employeeRepository).saveAll(employees);
    }
}

