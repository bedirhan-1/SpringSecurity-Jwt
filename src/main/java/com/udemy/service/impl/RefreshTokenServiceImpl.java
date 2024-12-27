package com.udemy.service.impl;

import com.udemy.jwt.AuthResponse;
import com.udemy.jwt.JwtService;
import com.udemy.jwt.RefreshTokenRequest;
import com.udemy.model.RefreshToken;
import com.udemy.model.User;
import com.udemy.repository.RefreshTokenRepository;
import com.udemy.repository.UserRepository;
import com.udemy.service.IRefreshTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }

    public boolean isRefreshTokenExpired(Date expirationDate) {
        return new Date().before(expirationDate);
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 5));
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        AuthResponse authResponse = new AuthResponse();
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.getRefreshToken());
        if (optional.isEmpty()) {
            return null;
        }

        RefreshToken refreshToken = optional.get();

        if (!isRefreshTokenExpired(refreshToken.getExpireDate())){
            System.out.println("Refresh token expired");
            refreshTokenRepository.delete(refreshToken);
        }

        String accessToken = jwtService.generateToken(refreshToken.getUser());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));

        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(savedRefreshToken.getRefreshToken());

        return authResponse;
    }
}
