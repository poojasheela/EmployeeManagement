package com.core.EmployeeManagement.service;

import com.core.EmployeeManagement.Employee;
import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.exception.EmployeeNotFoundException;
import com.core.EmployeeManagement.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
        private final EmployeeMapper mapper;
        private final List<Employee> employeeStore;

        public EmployeeServiceImpl(EmployeeMapper mapper) {
            this.mapper = mapper;
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
            emp.setId(employeeStore.size() + 1); // Simple ID generation, replace with a better approach for production
            employeeStore.add(emp);
            return mapper.toDTO(emp);
        }
    }
