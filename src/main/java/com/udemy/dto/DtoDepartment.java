package com.udemy.dto;

public class DtoDepartment {
    private Long id;
    private String location;
    private String name;

    public DtoDepartment(Long id, String location, String name) {
        this.id = id;
        this.location = location;
        this.name = name;
    }

    public DtoDepartment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
