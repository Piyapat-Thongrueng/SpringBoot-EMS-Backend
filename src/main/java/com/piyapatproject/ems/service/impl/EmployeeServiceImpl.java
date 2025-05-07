package com.piyapatproject.ems.service.impl;

import com.piyapatproject.ems.dto.EmployeeDto;
import com.piyapatproject.ems.entity.Employee;
import com.piyapatproject.ems.exception.ResourceNotFoundException;
import com.piyapatproject.ems.mapper.EmployeeMapper;
import com.piyapatproject.ems.repository.EmployeeRepository;
import com.piyapatproject.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("A requested employee does not exist with given id" + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
