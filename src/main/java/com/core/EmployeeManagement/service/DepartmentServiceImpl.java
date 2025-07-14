package com.core.EmployeeManagement.service;

import com.core.EmployeeManagement.Department;
import com.core.EmployeeManagement.Employee;
import com.core.EmployeeManagement.repository.DepartmentRepository;
import com.core.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public Department createDepartmentWithEmployees(String departmentName, List<Employee> employees) {
        Department department = new Department(departmentName);
        departmentRepository.save(department);
        for (Employee employee : employees) {
            employee.setDepartment(department);
        }
        employeeRepository.saveAll(employees);
        return department;
    }
}
