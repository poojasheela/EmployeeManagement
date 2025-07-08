package com.core.EmployeeManagement;

import com.core.EmployeeManagement.dto.EmployeeDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeDTOTest {
    @Test
    public void testDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFullName("Alice");
        dto.setContactEmail("alice@xyz.com");

        assertEquals("Alice", dto.getFullName());
        assertEquals("alice@xyz.com", dto.getContactEmail());
    }
}
