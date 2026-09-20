package com.example.api_rh.services;

import com.example.api_rh.dto.request.AuthRequestDTO;
import com.example.api_rh.dto.response.AcessResponseDTO;
import com.example.api_rh.security.UserDetailsImpl;
import com.example.api_rh.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager autenticacao;

    @Autowired
    private JwtUtils jwtUtils;

    public AcessResponseDTO login(AuthRequestDTO auth) {
        try {
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(auth.email(), auth.senha());

            Authentication authentication = autenticacao.authenticate(userAuth);

            UserDetailsImpl autenticarUsuario = (UserDetailsImpl) authentication.getPrincipal();

            assert autenticarUsuario != null;
            String token = jwtUtils.gerarToken(autenticarUsuario);
            String mensagem = "login realizado " + auth.email();
            return new AcessResponseDTO(token, mensagem);

        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException("email e senha inválidos");
        }
    }
}
