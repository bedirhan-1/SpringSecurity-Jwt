package com.udemy.service;

import com.udemy.jwt.AuthResponse;
import com.udemy.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {
    public AuthResponse refreshToken(RefreshTokenRequest refreshToken);
}
