package com.udemy.service.impl;

import com.udemy.dto.DtoDepartment;
import com.udemy.dto.DtoEmployee;
import com.udemy.model.Department;
import com.udemy.model.Employee;
import com.udemy.repository.EmployeeRepository;
import com.udemy.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public DtoEmployee findEmployeeById(Long id) {
        DtoEmployee dtoEmployee = new DtoEmployee();
        DtoDepartment dtoDepartment = new DtoDepartment();
        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {
            Employee employee = optional.get();
            Department department = employee.getDepartment();

            BeanUtils.copyProperties(employee, dtoEmployee);
            BeanUtils.copyProperties(department, dtoDepartment);
            dtoEmployee.setDepartment(dtoDepartment);

            return dtoEmployee;
        }

        return null;
    }


}
