package com.nick.spring.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "employees")
public class EmployeeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, length = 30)
    private String surname;
    @Column(nullable = false, length = 60,unique = true)
    private String email;
    @Column(nullable = false, length = 30)
    private String position;
    @Column(nullable = false, length = 50)
    private String location;
    @Column(nullable = false)
    private double salary;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
}
