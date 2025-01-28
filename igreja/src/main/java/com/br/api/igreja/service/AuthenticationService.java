package com.br.api.igreja.service;

import com.br.api.igreja.entities.User;
import com.br.api.igreja.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Método de login (já existente)
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        return jwtService.generateToken(user.getEmail());
    }

    // Novo método de registro de usuário
    public String register(String nome, String email, String password) {
        // Verifica se o e-mail já está registrado
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("E-mail já registrado");
        }

        // Criptografa a senha
        String encodedPassword = passwordEncoder.encode(password);

        // Cria o novo usuário
        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setPassword(encodedPassword);

        // Salva o usuário no banco de dados
        userRepository.save(user);

        // Gera o token JWT para o novo usuário
        return jwtService.generateToken(user.getEmail());
    }
}