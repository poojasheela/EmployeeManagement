package com.core.EmployeeManagement.service;
import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.entity.Employee;
import com.core.EmployeeManagement.exception.EmployeeNotFoundException;
import com.core.EmployeeManagement.mapper.EmployeeMapper;
import com.core.EmployeeManagement.repository.DepartmentRepository;
import com.core.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper mapper;
    @Autowired
    EmployeeRepository employeeRepository;



    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee emp = mapper.toEntity(dto);
        employeeRepository.save(emp);
        return mapper.toDTO(emp);
    }

    @Override
    public List<EmployeeDTO> fetchAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return mapper.toDTOList(employees);
    }

    @Override
    public List<EmployeeDTO> fetchEmployeesByEmailDomain() {
        List<Employee> employees = employeeRepository.findEmployeesByEmailDomain("@qrs.com");
        return mapper.toDTOList(employees);
    }

    @Override
    public List<EmployeeDTO> fetchEmployeesByName(String name) {
        List<Employee> employees = employeeRepository.findEmployeesByName(name);
        return mapper.toDTOList(employees);
    }
//
//    @Override
//    public Page<EmployeeDTO> fetchPaginatedEmployees(int page, int size, String sortBy, String sortDirection) {
//        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
//        Pageable pageable = PageRequest.of(page, size, direction, sortBy);
//        return employeeRepository.findAll(pageable);
//    }

    @Override
    public Page<EmployeeDTO> fetchPaginatedEmployees(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, direction, sortBy);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(mapper::toDTO);
    }


    @Override
    public EmployeeDTO updateEmployeeById(int id, EmployeeDTO dto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        existingEmployee.setName(dto.getFullName());
        existingEmployee.setEmail(dto.getContactEmail());
        employeeRepository.save(existingEmployee);
        return mapper.toDTO(existingEmployee);
    }


    @Override
    public void deleteEmployeeById(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}
