package com.core.EmployeeManagement;

import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.mapper.EmployeeMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeMapperTest {
    private final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    public void testToEntity() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFullName("Alice");
        dto.setContactEmail("alice@qrs.com");

        Employee entity = mapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals("Alice", entity.getName());
        assertEquals("alice@qrs.com", entity.getEmail());
    }

    @Test
    public void testToDTO() {
        Employee entity = new Employee();
        entity.setName("Bob");
        entity.setEmail("bob@xyz.com");

        EmployeeDTO dto = mapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals("Bob", dto.getFullName());
        assertEquals("bob@xyz.com", dto.getContactEmail());
    }

    @Test
    public void testToDTOList() {
        Employee emp = new Employee();
        emp.setName("Charlie");
        emp.setEmail("charlie@qrs.com");

        List<EmployeeDTO> dtos = mapper.toDTOList(Collections.singletonList(emp));

        assertEquals(1, dtos.size());
        assertEquals("Charlie", dtos.getFirst().getFullName());
    }
}


