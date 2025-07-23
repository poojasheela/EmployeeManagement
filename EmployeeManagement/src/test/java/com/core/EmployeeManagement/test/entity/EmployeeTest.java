package com.core.EmployeeManagement.test.entity;
import com.core.EmployeeManagement.entity.Department;
import com.core.EmployeeManagement.entity.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    void testAllArgsConstructor() {
        Department dept = new Department(100L, "Sales");
        Employee emp = new Employee(50, "Roy", "roy@gmail.com", "roy123", dept);

        assertEquals(50, emp.getId());
        assertEquals("Roy", emp.getName());
        assertEquals("roy@gmail.com", emp.getEmail());
        assertEquals("roy123", emp.getPassword());
        assertEquals(dept, emp.getDepartment());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        Department dept = new Department(101L, "Support");
        Employee emp = new Employee();
        emp.setId(10);
        emp.setName("Alice");
        emp.setEmail("alice@mail.com");
        emp.setPassword("alice123");
        emp.setDepartment(dept);

        assertEquals(10, emp.getId());
        assertEquals("Alice", emp.getName());
        assertEquals("alice@mail.com", emp.getEmail());
        assertEquals("alice123", emp.getPassword());
        assertEquals(dept, emp.getDepartment());
    }

    @Test
    void testEqualsAndHashCode() {
        Department dept = new Department(200L, "Admin");
        Employee e1 = new Employee(1, "Sam", "sam@mail.com", "pass1", dept);
        Employee e2 = new Employee(1, "Sam", "sam@mail.com", "pass1", dept);

        assertEquals(e1, e2);
    }

    @Test
    void testToStringContainsEmail() {
        Employee emp = new Employee(2, "Ann", "ann@mail.com", "pwd", null);
        assertTrue(emp.toString().contains("ann@mail.com"));
    }
}
