package com.core.EmployeeManagement.service;

import com.core.EmployeeManagement.Employee;
import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.exception.EmployeeNotFoundException;
import com.core.EmployeeManagement.mapper.EmployeeMapper;
import com.core.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public  class EmployeeServiceImpl implements EmployeeService {
        private final EmployeeMapper mapper;
        private final List<Employee> employeeStore;
    private final EmployeeRepository employeeRepository;

        public EmployeeServiceImpl(EmployeeMapper mapper, EmployeeRepository employeeRepository) {
            this.mapper = mapper;
            this.employeeRepository = employeeRepository;
            this.employeeStore = new ArrayList<>();
            initializeEmployees();
        }

        private void initializeEmployees() {
            employeeStore.addAll(List.of(
                    new Employee(1, "Alice", "alice@xyz.com", "pass1"),
                    new Employee(2, "Bob", "bob@qrs.com", "pass2"),
                    new Employee(3, "Charlie", "charlie@qrs.com", "pass3"),
                    new Employee(4, "David", "david@xyz.com", "pass4")
            ));
        }

        @Override
        public List<EmployeeDTO> getAllEmployees() {
            return mapper.toDTOList(employeeStore);
        }

        @Override
        public List<EmployeeDTO> getEmployeesByQrsDomain() {
            List<Employee> filtered = employeeStore.stream()
                    .filter(emp -> emp.getEmail().toLowerCase().contains("@qrs.com"))
                    .collect(Collectors.toList());

            if (filtered.isEmpty()) {
                throw new EmployeeNotFoundException("No employees found with @qrs.com domain");
            }

            return mapper.toDTOList(filtered);
        }

        @Override
        public EmployeeDTO createEmployee(EmployeeDTO dto) {
            Employee emp = mapper.toEntity(dto);
            emp.setId(employeeStore.size() + 1);
            employeeStore.add(emp);
            return mapper.toDTO(emp);
        }

    @Override
    public List<EmployeeDTO> getEmployeesByDomain() {

        List<Employee> employees = employeeRepository.findEmployeesByEmailDomain("@qrs.com");
        return mapper.toDTOList(employees);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByName(String name) {

        List<Employee> employees = employeeRepository.findEmployeesByName(name);
        return mapper.toDTOList(employees);
    }


    @Override
    public Page<EmployeeDTO> getAll(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
       //return mapper.toDTOList(employees);
        return employees.map(mapper::toDTO);
    }

}

