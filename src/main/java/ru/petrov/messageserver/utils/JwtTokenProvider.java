package ru.petrov.messageserver.utils;


import org.springframework.security.core.userdetails.User;

public interface JwtTokenProvider {
    User verifyAccessToken(String token);
}
