package com.core.EmployeeManagement;

import org.junit.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EmployeeTest {

    @Test
    public void testEmployeeConstructor() {

        Employee employee = new Employee();
        assertNotNull(employee);
    }

    @Test
    public void testEmployeeParameterizedConstructor() {

        Employee employee = new Employee(1, "Alice", "alice@xyz.com", "password123");
        assertEquals(1, employee.getId());
        assertEquals("Alice", employee.getName());
        assertEquals("alice@xyz.com", employee.getEmail());
        assertEquals("password123", employee.getPassword());
    }

    @Test
    public void testSettersAndGetters() {

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Bob");
        employee.setEmail("bob@xyz.com");
        employee.setPassword("password123");

        assertEquals(1, employee.getId());
        assertEquals("Bob", employee.getName());
        assertEquals("bob@xyz.com", employee.getEmail());
        assertEquals("password123", employee.getPassword());
    }

    @Test
    public void testSetAndGetId() {
        Employee employee = new Employee();
        employee.setId(10);
        assertEquals(10, employee.getId());
    }

    @Test
    public void testSetAndGetName() {
        Employee employee = new Employee();
        employee.setName("David");
        assertEquals("David", employee.getName());
    }

    @Test
    public void testSetAndGetEmail() {
        Employee employee = new Employee();
        employee.setEmail("david@xyz.com");
        assertEquals("david@xyz.com", employee.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        Employee employee = new Employee();
        employee.setPassword("pass1");
        assertEquals("pass1", employee.getPassword());
    }

}

