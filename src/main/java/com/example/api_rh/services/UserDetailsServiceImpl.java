package com.example.api_rh.services;

import com.example.api_rh.dao.UsuarioDAO;
import com.example.api_rh.entity.Usuario;
import com.example.api_rh.security.UserDetailsImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioDAO usuarioDAO;

    public UserDetailsServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioDAO.findByEmail(email);

            return new UserDetailsImpl(
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getAtivo(),
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("usuario com o email " + email + " nao econtrado");
        }
    }
}
