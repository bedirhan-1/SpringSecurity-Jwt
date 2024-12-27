package com.udemy.service.impl;


import com.udemy.dto.DtoUser;
import com.udemy.jwt.AuthRequest;
import com.udemy.jwt.AuthResponse;
import com.udemy.jwt.JwtService;
import com.udemy.model.RefreshToken;
import com.udemy.model.User;
import com.udemy.repository.RefreshTokenRepository;
import com.udemy.repository.UserRepository;
import com.udemy.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    AuthServiceImpl(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            AuthenticationProvider authenticationProvider,
            JwtService jwtService,
            RefreshTokenRepository refreshTokenRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.jwtService = jwtService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(usernamePasswordAuthenticationToken);

            Optional<User> user = userRepository.findByUsername(request.getUsername());
            if (user.isPresent()) {
                String accessToken = jwtService.generateToken(user.get());
                RefreshToken refreshToken = refreshTokenRepository.save(createRefreshToken(user.get()));
                return new AuthResponse(accessToken, refreshToken.getRefreshToken());
            }

            return null;
        }catch (Exception e) {
            System.out.println("[credential error]" + e.getMessage());
        }
        return null;
    }

    @Override
    public DtoUser register(AuthRequest request) {
        DtoUser dto = new DtoUser();
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser =  userRepository.save(user);
        BeanUtils.copyProperties(savedUser, dto);
        return dto;
    }

}
