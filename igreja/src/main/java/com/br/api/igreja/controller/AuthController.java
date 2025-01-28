package com.br.api.igreja.controller;

import com.br.api.igreja.dto.LoginRequest;
import com.br.api.igreja.dto.RegisterRequest;
import com.br.api.igreja.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Erro de autenticação: " + e.getMessage());
        }
    }

    // Novo endpoint de registro
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            String token = authenticationService.register(registerRequest.getNome(), registerRequest.getEmail(), registerRequest.getPassword());
            return ResponseEntity.ok(token);  // Retorna o token JWT
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Erro ao cadastrar: " + e.getMessage());
        }
    }
}