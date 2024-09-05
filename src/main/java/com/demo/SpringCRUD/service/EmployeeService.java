package com.demo.SpringCRUD.service;

import com.demo.SpringCRUD.dto.EmployeeDTO;
import com.demo.SpringCRUD.model.Employee;
import com.demo.SpringCRUD.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::convertToDTO);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        updateEmployeeEntity(employee, employeeDetails);
        Employee updatedEmployee = employeeRepository.save(employee);

        return convertToDTO(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getEmployeeNumber(),
                employee.getInitials(),
                employee.getLastName(),
                employee.getDesignation(),
                employee.isActiveStatus()
        );
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setEmployeeNumber(employeeDTO.getEmployeeNumber());
        employee.setInitials(employeeDTO.getInitials());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setActiveStatus(employeeDTO.isActiveStatus());
        return employee;
    }

    private void updateEmployeeEntity(Employee employee, EmployeeDTO employeeDetails) {
        employee.setEmployeeNumber(employeeDetails.getEmployeeNumber());
        employee.setInitials(employeeDetails.getInitials());
        employee.setLastName(employeeDetails.getLastName());
        employee.setDesignation(employeeDetails.getDesignation());
        employee.setActiveStatus(employeeDetails.isActiveStatus());
    }
}
