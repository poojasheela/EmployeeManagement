package com.core.EmployeeManagement.dto;
import com.core.EmployeeManagement.entity.Employee;
import lombok.Data;
import java.util.List;

@Data
public class DepartmentDTO {
    private Long id;
    private String departmentName;
    private List<Employee> employees;

}
