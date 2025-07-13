package com.core.EmployeeManagement.mapper;

import com.core.EmployeeManagement.Employee;
import com.core.EmployeeManagement.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "contactEmail", target = "email")
    Employee toEntity(EmployeeDTO dto);

    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "email", target = "contactEmail")
    EmployeeDTO toDTO(Employee entity);

    List<EmployeeDTO> toDTOList(List<Employee> employees);
}