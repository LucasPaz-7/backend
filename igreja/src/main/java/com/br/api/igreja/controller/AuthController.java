package com.br.api.igreja.controller;

import com.br.api.igreja.dto.LoginRequest;
import com.br.api.igreja.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Login solicitado para o email: {}", loginRequest.getEmail());

        try {
            String token = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(token);  // Retorna o token JWT
        } catch (RuntimeException e) {
            // Log detalhado do erro
            logger.error("Erro durante a autenticação: {}", e.getMessage());

            // Resposta mais detalhada com código 401 (não autorizado)
            if (e.getMessage().contains("senha")) {
                return ResponseEntity.status(401).body("Senha incorreta");
            } else if (e.getMessage().contains("usuário")) {
                return ResponseEntity.status(401).body("Usuário não encontrado");
            } else {
                return ResponseEntity.status(401).body("Erro de autenticação: " + e.getMessage());
            }
        }
    }
}
