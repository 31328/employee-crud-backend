package com.nick.spring.converter;

import com.nick.spring.dto.EmployeeDto;
import com.nick.spring.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {
    public EmployeeDto entityToDto(EmployeeEntity employeeEntity){
        EmployeeDto returnValue = new EmployeeDto();
        returnValue.setId(employeeEntity.getId());
        returnValue.setName(employeeEntity.getName());
        returnValue.setSurname(employeeEntity.getSurname());
        returnValue.setEmail(employeeEntity.getEmail());
        returnValue.setPosition(employeeEntity.getPosition());
        returnValue.setLocation(employeeEntity.getLocation());
        returnValue.setSalary(employeeEntity.getSalary());
        return returnValue;
    }

    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto){
        EmployeeEntity returnValue = new EmployeeEntity();
        returnValue.setId(employeeDto.getId());
        returnValue.setName(employeeDto.getName());
        returnValue.setSurname(employeeDto.getSurname());
        returnValue.setEmail(employeeDto.getEmail());
        returnValue.setPosition(employeeDto.getPosition());
        returnValue.setLocation(employeeDto.getLocation());
        returnValue.setSalary(employeeDto.getSalary());
        return returnValue;
    }
}
