package com.example.petshop.auth;

import com.example.petshop.base.Usuario;
import com.example.petshop.config.JwtService;
import com.example.petshop.repository.UsuarioRepository;
import com.example.petshop.token.Token;
import com.example.petshop.token.TokenRepository;
import com.example.petshop.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .cpf(request.getCpf())
                .telefone(request.getTelefone())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .dataNascimento(request.getDataNascimento())
                .role(request.getRole())
                .build();
        var savedUsuario = usuarioRepository.save(usuario);
        var jwtToken = jwtService.generateToken(usuario);
        var refreshToken = jwtService.generateRefreshToken(usuario);
        saveUsuarioToken(savedUsuario, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var usuario = usuarioRepository.findUsuarioByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(usuario);
        var refreshToken = jwtService.generateRefreshToken(usuario);
        revokeAllUsuarioTokens(usuario);
        saveUsuarioToken(usuario, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUsuarioToken(Usuario usuario, String jwtToken) {
        var token = Token.builder()
                .usuario(usuario)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUsuarioTokens(Usuario usuario) {
        var validUsuarioTokens = tokenRepository.findAllValidTokenByUser(usuario.getId());
        if (validUsuarioTokens.isEmpty())
            return;
        validUsuarioTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUsuarioTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String usuarioEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        usuarioEmail = jwtService.extractUsername(refreshToken);
        if (usuarioEmail != null) {
            var usuario = this.usuarioRepository.findUsuarioByEmail(usuarioEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, usuario)) {
                var accessToken = jwtService.generateToken(usuario);
                revokeAllUsuarioTokens(usuario);
                saveUsuarioToken(usuario, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}