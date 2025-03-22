package com.lms.api.auth.service;

import com.lms.api.auth.config.security.JwtService;
import com.lms.api.auth.dto.AuthenticationRequest;
import com.lms.api.auth.dto.AuthenticationResponse;
import com.lms.api.auth.dto.RegisterRequest;
import com.lms.api.auth.dto.RegistrationResponse;
import com.lms.api.auth.model.User;
import com.lms.api.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public RegistrationResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail().toLowerCase()).isPresent()) {
            return RegistrationResponse.builder()
                    .message("Registration Failed,User Exist")
                    .responseCode("01")
                    .build();
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(hashedPassword)
                .build();
        userRepository.save(user);

        return RegistrationResponse.builder()
                .message("Registration Successful.")
                .responseCode("00")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
        }

        var jwtToken  = jwtService.generateToken(user);

        userRepository.save(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .message("Authentication successful")
                .build();
    }
}
