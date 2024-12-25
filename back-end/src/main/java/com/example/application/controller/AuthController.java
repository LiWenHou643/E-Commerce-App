package com.example.application.controller;

import com.example.application.dto.ApiResponse;
import com.example.application.dto.AuthDTO;
import com.example.application.dto.CreateUserRequest;
import com.example.application.dto.UserDTO;
import com.example.application.service.AuthService;
import com.example.application.service.LogoutHandlerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;
    private final LogoutHandlerService logoutHandlerService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthDTO>> login(@RequestBody AuthDTO loginRequest, HttpServletResponse httpServletResponse) {
        var response = authService.authenticate(loginRequest, httpServletResponse);
        log.info("User authenticated successfully");
        return ResponseEntity.ok(
                new ApiResponse<>("success", "User authenticated successfully", response));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody @Valid CreateUserRequest request) {
        try {
            var user = authService.register(request);
            log.info("User registered successfully");
            return ResponseEntity.ok(
                    new ApiResponse<>("success", "User registered successfully", user));
        } catch (Exception e) {
            log.error("Error occurred while registering user", e);
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>("error", e.getMessage(), null));
        }
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<ApiResponse<AuthDTO>> refreshToken(HttpServletRequest httpServletRequest) {
        var response = authService.refreshToken(httpServletRequest);
        log.info("Token refreshed successfully");
        return ResponseEntity.ok(
                new ApiResponse<>("success", "Token refreshed successfully", response));
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutHandlerService.logout(request, response, authentication);
        log.info("User logged out successfully");
        return ResponseEntity.ok(
                new ApiResponse<>("success", "User logged out successfully", null));
    }
}