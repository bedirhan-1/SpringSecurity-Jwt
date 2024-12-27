package com.udemy.controller;

import com.udemy.dto.DtoUser;
import com.udemy.jwt.AuthRequest;
import com.udemy.jwt.AuthResponse;
import com.udemy.jwt.RefreshTokenRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface IRestAuthController {
    DtoUser register(AuthRequest authRequest);
    AuthResponse authenticate(@Valid @RequestBody AuthRequest authRequest);
    AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest authRequest);
}
