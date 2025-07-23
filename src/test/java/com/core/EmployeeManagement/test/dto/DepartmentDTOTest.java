package com.core.EmployeeManagement.test.dto;
import com.core.EmployeeManagement.dto.DepartmentDTO;
import com.core.EmployeeManagement.entity.Employee;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidDTO() {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(1L);
        dto.setDepartmentName("HR");
        dto.setEmployees(List.of(new Employee()));

        Set<ConstraintViolation<DepartmentDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testBlankDepartmentName() {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDepartmentName("");

        Set<ConstraintViolation<DepartmentDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }
}
