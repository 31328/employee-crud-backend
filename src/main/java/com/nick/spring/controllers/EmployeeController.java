package com.nick.spring.controllers;

import com.nick.spring.dto.EmployeeDto;
import com.nick.spring.exceptions.DataNotValidatedException;
import com.nick.spring.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.listAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Integer employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee with id: " + employeeId + " has been removed", HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeAll")
    public ResponseEntity<String> deleteAllEmployees(){
        employeeService.deleteAll();
        return new ResponseEntity<>("Employees deleted from database", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody @Validated EmployeeDto employee, Errors errors){
        if (errors.hasErrors()){
            throw new DataNotValidatedException("Employee has not been validated");
        }
        employeeService.addEmployee(employee);
        return new ResponseEntity<>("Employee has been created", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody @Validated EmployeeDto employee, @PathVariable("id") Integer employeeId, Errors errors){
        if (errors.hasErrors()){
            throw new RuntimeException("Employee has not been validate");
        }
        employeeService.updateEmployee(employee, employeeId);
        return new ResponseEntity<>("Employee with id: " + employeeId + " has been updated.", HttpStatus.OK);
    }

    @GetMapping(value = "/maximumSalary")
    public ResponseEntity<Double> getMaximumSalary() {
        return new ResponseEntity<>(employeeService.findMaximumSalary(),HttpStatus.OK);
    }

}
