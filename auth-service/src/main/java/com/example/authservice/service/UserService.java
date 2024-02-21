package com.example.authservice.service;

import com.example.authservice.dto.AuthResponse;
import com.example.authservice.dto.LoginRequest;
import com.example.authservice.dto.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    AuthResponse login(LoginRequest loginRequest);

    void register(RegisterRequest registerRequest);
}
