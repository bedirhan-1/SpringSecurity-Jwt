package com.udemy.controller;

import com.udemy.dto.DtoEmployee;

public interface IEmployeeController {
    DtoEmployee findEmployeeById(Long id);
}
