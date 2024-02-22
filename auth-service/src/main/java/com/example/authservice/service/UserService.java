package com.example.authservice.service;

import com.example.authservice.dto.AuthResponse;
import com.example.authservice.dto.LoginRequest;
import com.example.authservice.dto.MessageResponse;
import com.example.authservice.dto.RegisterRequest;

public interface UserService {

    AuthResponse login(LoginRequest loginRequest);

    MessageResponse register(RegisterRequest registerRequest);
}
