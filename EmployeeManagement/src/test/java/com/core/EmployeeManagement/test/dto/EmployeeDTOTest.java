package com.core.EmployeeManagement.test.dto;

import com.core.EmployeeManagement.dto.EmployeeDTO;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO("Roy", "roy@gmail.com");
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidEmail() {
        EmployeeDTO dto = new EmployeeDTO("Roy", "invalid-email");
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testBlankName() {
        EmployeeDTO dto = new EmployeeDTO("", "roy@gmail.com");
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }
}
