package com.example.authservice.service.impl;

import com.example.authservice.config.JwtGenerator;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.dto.MessageResponse;
import com.example.authservice.dto.RegisterRequest;
import com.example.authservice.exception.ResourceNotFoundException;
import com.example.authservice.dto.LoginRequest;
import com.example.authservice.model.Role;
import com.example.authservice.model.UserEntity;
import com.example.authservice.repository.RoleRepository;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.example.authservice.util.Constant.REGISTERED_SUCCESSFULLY;
import static com.example.authservice.util.Constant.ROLE_NOT_FOUND;
import static com.example.authservice.util.Constant.USER;
import static com.example.authservice.util.Constant.USER_EXISTS;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new AuthResponse(jwtGenerator.generateToken(authentication));
    }

    @Override
    public MessageResponse register(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        if (userRepository.existsByUsername(username)) {
            return MessageResponse.of(
                    String.format(USER_EXISTS, username));
        }
        UserEntity user = UserEntity.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode((registerRequest.getPassword())))
                .roles(Collections.singletonList(findRoleOrThrow(USER)))
                .build();
        userRepository.save(user);
        return MessageResponse.of(REGISTERED_SUCCESSFULLY);
    }

    private Role findRoleOrThrow(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ROLE_NOT_FOUND, roleName)));
    }
}
