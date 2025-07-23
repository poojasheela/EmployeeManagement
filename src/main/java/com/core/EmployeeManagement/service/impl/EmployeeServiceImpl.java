package com.core.EmployeeManagement.service;
import com.core.EmployeeManagement.dto.EmployeeDTO;
import com.core.EmployeeManagement.entity.Employee;
import com.core.EmployeeManagement.exception.DataConflictException;
import com.core.EmployeeManagement.exception.EmployeeNotFoundException;
import com.core.EmployeeManagement.exception.InvalidRequestException;
import com.core.EmployeeManagement.mapper.EmployeeMapper;
import com.core.EmployeeManagement.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper mapper;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        try {
            if (dto.getFullName() == null || dto.getFullName().trim().isEmpty() ||
                    dto.getContactEmail() == null || dto.getContactEmail().trim().isEmpty()) {
                throw new InvalidRequestException("Full name and email must be provided");
            }
            if (employeeRepository.existsByEmail(dto.getContactEmail())) {
                throw new DataConflictException("Employee with this email id exists");
            }
            Employee emp = mapper.toEntity(dto);
            Employee saved = employeeRepository.save(emp);
            return mapper.toDTO(saved);
        } catch (Exception e) {
            log.error("Error while creating employee: {}", e.getMessage());
            throw e;
        }
    }
        @Override
    public List<EmployeeDTO> fetchAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            return mapper.toDTOList(employees);
        }catch (Exception e){
            log.error("Error while fetching employee : {}",e.getMessage());
            throw e;
        }
    }

    @Override
    public List<EmployeeDTO> fetchEmployeesByEmailDomain() {
        try{
        List<Employee> employees = employeeRepository.findEmployeesByEmailDomain("@qrs.com");
        return mapper.toDTOList(employees);
        } catch (Exception e) {
        log.error("Error fetching employees by domain: {}", e.getMessage());
        throw e;
        }
    }

    @Override
    public List<EmployeeDTO> fetchEmployeesByName(String name) {
        try {
            List<Employee> employees = employeeRepository.findEmployeesByName(name);
            return mapper.toDTOList(employees);
        } catch (Exception e) {
            log.error("Error fetching employees by name : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<EmployeeDTO> fetchPaginatedEmployees(int page, int size, String sortBy, String sortDirection) {
        try {
            Sort.Direction direction = Sort.Direction.fromString(sortDirection);
            Pageable pageable = PageRequest.of(page, size, direction, sortBy);
            Page<Employee> employeePage = employeeRepository.findAll(pageable);
            return employeePage.map(mapper::toDTO);
        } catch (Exception e) {
            log.error("Error fetching paginated employees : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public EmployeeDTO updateEmployeeById(int id, EmployeeDTO dto) {
        try {
            Employee existingEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id :"+ id));
            if (dto.getFullName() == null || dto.getContactEmail() == null) {
                throw new InvalidRequestException("Full name and email must be provided for update.");
            }

            if (employeeRepository.existsByEmail(dto.getContactEmail()) &&
                    !existingEmployee.getEmail().equals(dto.getContactEmail())) {
                throw new DataConflictException("Email already used by another employee.");
            }
            
            existingEmployee.setName(dto.getFullName());
            existingEmployee.setEmail(dto.getContactEmail());
            employeeRepository.save(existingEmployee);
            return mapper.toDTO(existingEmployee);
        } catch (Exception e) {
            log.error("Error in updating employees by id : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        try {
            if (!employeeRepository.existsById(id)) {
                throw new EmployeeNotFoundException("Employee not found");
            }
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error in deleting employees : {}", e.getMessage());
            throw e;
        }
    }
}
