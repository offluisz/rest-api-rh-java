package com.example.api_rh.services;

import com.example.api_rh.dao.AumentoDAOImpl;
import com.example.api_rh.dao.UsuarioDAOImpl;
import com.example.api_rh.dto.request.AumentoRequestDTO;
import com.example.api_rh.dto.response.AcessResponseDTO;
import com.example.api_rh.entity.Aumento;
import com.example.api_rh.entity.Usuario;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class AumentoService {

    private final AumentoDAOImpl aumentoDAO;

    private final UsuarioDAOImpl usuarioDAO;

    public AumentoService(AumentoDAOImpl aumentoDAO, UsuarioDAOImpl usuarioDAO) {
        this.aumentoDAO = aumentoDAO;
        this.usuarioDAO = usuarioDAO;
    }

    @Transactional
    public void aplicarAumento(double percentual) {
        try {
            Authentication usuarioAutenticado = SecurityContextHolder.getContext().getAuthentication();
            String email = usuarioAutenticado.getName();
            Usuario usuario = usuarioDAO.findByEmail(email);

            int result = aumentoDAO.exec(percentual);

            if(result == 0) {
                throw new RuntimeException("erro na transação");
            }

            aumentoDAO.save(OffsetDateTime.now(),
                    percentual,
                    usuario.getId()
                    );
        }
        catch (EmptyResultDataAccessException e) {
            throw new BadCredentialsException("usuario sem permissao para realizar essa ação");
        }
    }
}
