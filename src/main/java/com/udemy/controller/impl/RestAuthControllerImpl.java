package com.udemy.controller.impl;

import com.udemy.controller.IRestAuthController;
import com.udemy.dto.DtoUser;
import com.udemy.jwt.AuthRequest;
import com.udemy.jwt.AuthResponse;
import com.udemy.jwt.RefreshTokenRequest;
import com.udemy.service.IAuthService;
import com.udemy.service.IRefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    private final IAuthService authService;
    private final IRefreshTokenService refreshTokenService;
    RestAuthControllerImpl(IAuthService authService, IRefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
        this.authService = authService;
    }

    @PostMapping("/register")
    @Override
    public DtoUser register(@Valid @RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

    @PostMapping("/refresh-token")
    @Override
    public AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest authRequest) {
        return refreshTokenService.refreshToken(authRequest);
    }


}
