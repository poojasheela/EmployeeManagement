package com.core.EmployeeManagement.mapper;


import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping( target = "fullName",source = "name")
    @Mapping( target = "contactEmail",source = "email")
    EmployeeDTO toDTO(Employee employee);


    @Mapping( target = "name",source = "fullName")
    @Mapping( target = "email",source = "contactEmail")
    Employee toEntity(EmployeeDTO employeeDTO);

    List<EmployeeDTO> toDTOList(List<Employee> employees);
}