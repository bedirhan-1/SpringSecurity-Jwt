package com.udemy.service;

import com.udemy.dto.DtoEmployee;

public interface IEmployeeService {
    DtoEmployee findEmployeeById(Long id);
}
