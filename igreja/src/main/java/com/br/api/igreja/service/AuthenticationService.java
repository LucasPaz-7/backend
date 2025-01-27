package com.br.api.igreja.service;

import com.br.api.igreja.entities.User;
import com.br.api.igreja.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String login(String email, String password) {
        // Busca o usuário no banco de dados
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Log para debugar a senha fornecida e a senha armazenada
        System.out.println("Senha fornecida (em texto plano): " + password);
        System.out.println("Senha armazenada no banco (em texto claro): " + user.getPassword());

        // Verifica se a senha fornecida corresponde à senha armazenada (sem criptografia)
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        // Se a senha estiver correta, gera o token JWT
        return jwtService.generateToken(user.getEmail());
    }
}
