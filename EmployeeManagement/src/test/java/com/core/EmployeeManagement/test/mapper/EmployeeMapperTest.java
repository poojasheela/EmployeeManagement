package com.core.EmployeeManagement.test.mapper;
import com.core.EmployeeManagement.entity.Department;
import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.entity.Employee;
import com.core.EmployeeManagement.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeMapperTest {

    private final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    void testToDTO() {
        Department department = new Department(100L, "Sales");
        Employee employee = new Employee(50, "Roy", "roy@gmail.com", "roy123",department);

        EmployeeDTO dto = mapper.toDTO(employee);

        assertEquals("Roy", dto.getFullName());
        assertEquals("roy@gmail.com", dto.getContactEmail());
    }

    @Test
    void testToEntity() {
        EmployeeDTO dto = new EmployeeDTO("Roy", "roy@gmail.com");

        Employee employee = mapper.toEntity(dto);

        assertEquals("Roy", employee.getName());
        assertEquals("roy@gmail.com", employee.getEmail());
    }

    @Test
    void testToDTOList() {
        Employee emp1 = new Employee(50, "Roy", "roy@gmail.com", "roy123", new Department(100L, "Sales"));
        Employee emp2 = new Employee(51, "John", "john@gmail.com", "john123", new Department(101L, "HR"));

        List<EmployeeDTO> dtoList = mapper.toDTOList(List.of(emp1, emp2));

        assertEquals(2, dtoList.size());
        assertEquals("Roy", dtoList.get(0).getFullName());
        assertEquals("john@gmail.com", dtoList.get(1).getContactEmail());
    }
}
