package com.nick.spring.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public class EmployeeDto implements Serializable {
    private Integer id;
    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;
    @NotEmpty
    @Size(min = 3, max = 30)
    private String surname;
    @NotEmpty
    @Size(min = 3, max = 60)
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 30)
    private String position;
    @NotEmpty
    @Size(min = 3, max = 50)
    private String location;
    @NotNull
    @Positive
    @DecimalMin(value = "845.00")
    private double salary;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeDto(String name, String surname, String email, String position, String location, double salary) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.position = position;
        this.location = location;
        this.salary = salary;
    }

    public EmployeeDto() {
    }
}
