package com.core.EmployeeManagement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Employee {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private int id;
    @Setter
    @Getter
    public String name;
    @Setter
    @Getter
    public String email;
    @Setter
    @Getter
    private String password;

    public Employee() {}

    public Employee(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public void setDepartment(Department department) {
        this.department=department;
    }
}