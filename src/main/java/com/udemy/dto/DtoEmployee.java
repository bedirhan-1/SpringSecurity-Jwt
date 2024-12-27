package com.udemy.dto;

public class DtoEmployee {
    private Long id;
    private String firstName;
    private String lastName;
    private DtoDepartment department;

    public DtoEmployee(Long id, String firstName, String lastName, DtoDepartment department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public DtoEmployee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DtoDepartment getDepartment() {
        return department;
    }

    public void setDepartment(DtoDepartment department) {
        this.department = department;
    }
}