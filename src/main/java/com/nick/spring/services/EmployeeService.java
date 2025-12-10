package com.nick.spring.services;

import com.nick.spring.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> listAll();

    EmployeeDto getEmployeeById(Integer employeeId);

    EmployeeDto addEmployee(EmployeeDto employee);

    EmployeeDto updateEmployee(EmployeeDto employee, Integer employeeId);

    void deleteEmployee(Integer employeeId);

    void deleteAll();

    double findMaximumSalary();
}
