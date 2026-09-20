package com.example.api_rh.dao;

import com.example.api_rh.entity.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO{

    private final JdbcTemplate jdbc;

    public UsuarioDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Usuario findById(Long id) {
        String sql = "select * from tb_usuariorh where usu_id = ?";

        return jdbc.queryForObject(sql, (rs, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("usu_id"));
            usuario.setNome(rs.getString("usu_nome"));
            usuario.setEmail(rs.getString("usu_email"));
            usuario.setSenha(rs.getString("usu_senha"));
            usuario.setAtivo(rs.getString("usu_ativo"));

            return usuario;
        }, id);
    }

    @Override
    public Usuario findByEmail(String email) {
        String sql = "select * from tb_usuariorh where usu_email = ?";

        return jdbc.queryForObject(sql, (rs, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("usu_id"));
            usuario.setNome(rs.getString("usu_nome"));
            usuario.setEmail(rs.getString("usu_email"));
            usuario.setSenha(rs.getString("usu_senha"));
            usuario.setAtivo(rs.getString("usu_ativo"));


            return usuario;
        }, email);
    }
}
