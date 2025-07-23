package com.core.EmployeeManagement.test.entity;
import com.core.EmployeeManagement.entity.Department;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {

    @Test
    void testAllArgsConstructor() {
        Department department = new Department(100L, "Sales");
        assertEquals(Long.valueOf(100L), department.getId());
        assertEquals("Sales", department.getName());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        Department department = new Department();
        department.setId(200L);
        department.setName("HR");

        assertEquals(Long.valueOf(200L), department.getId());
        assertEquals("HR", department.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        Department d1 = new Department(1L, "Marketing");
        Department d2 = new Department(1L, "Marketing");

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testToString() {
        Department department = new Department(5L, "Finance");
        assertTrue(department.toString().contains("Finance"));
    }
}
