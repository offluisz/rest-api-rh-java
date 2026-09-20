package com.example.api_rh.dao;

import com.example.api_rh.entity.Usuario;

public interface UsuarioDAO {

    Usuario findById(Long id);

    Usuario findByEmail(String email);
}
