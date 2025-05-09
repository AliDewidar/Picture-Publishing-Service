package com.pioneers.PicturePublishingService.controller;

import com.pioneers.PicturePublishingService.model.dto.UserDto;
import com.pioneers.PicturePublishingService.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerApi(@Valid @RequestBody UserDto userDto) {
        authService.register(userDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginApi(@Valid @RequestBody UserDto userDto) {
        authService.login(userDto);
        return ResponseEntity.ok("User logged in successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutApi(@Valid @RequestParam String email) {
        authService.logout(email);
        return ResponseEntity.ok("User logged out successfully");
    }
}
