package com.udemy.jwt;

public class RefreshTokenRequest {
    private String refreshToken;

    RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    RefreshTokenRequest() {}

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
