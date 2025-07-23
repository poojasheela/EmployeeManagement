package com.core.EmployeeManagement.service.impl;
import com.core.EmployeeManagement.entity.Department;
import com.core.EmployeeManagement.entity.Employee;
import com.core.EmployeeManagement.repository.DepartmentRepository;
import com.core.EmployeeManagement.repository.EmployeeRepository;
import com.core.EmployeeManagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Department createDepartmentWithEmployees( String departmentName, List<Employee> employees) {
        Department department = new Department();
        department.setName(departmentName);
        Department savedDept = departmentRepository.save(department);
        for (Employee emp : employees) {
            emp.setDepartment(savedDept);
        }
        employeeRepository.saveAll(employees);
        return savedDept;
    }
}