package com.udemy.controller.impl;

import com.udemy.controller.IEmployeeController;
import com.udemy.dto.DtoEmployee;
import com.udemy.service.IEmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeControllerImpl implements IEmployeeController {

    private final IEmployeeService employeeService;
    public EmployeeControllerImpl(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    @Override
    public DtoEmployee findEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }
}
