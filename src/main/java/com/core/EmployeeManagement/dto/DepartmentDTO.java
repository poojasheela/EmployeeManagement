package com.core.EmployeeManagement.dto;

import com.core.EmployeeManagement.Employee;

import java.util.List;

public class DepartmentDTO {

    private String departmentName;
    private List<Employee> employees;


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
