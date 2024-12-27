package com.udemy.service;

import com.udemy.dto.DtoUser;
import com.udemy.jwt.AuthRequest;
import com.udemy.jwt.AuthResponse;

public interface IAuthService {
    public DtoUser register(AuthRequest request);
    public AuthResponse authenticate(AuthRequest request);
}
