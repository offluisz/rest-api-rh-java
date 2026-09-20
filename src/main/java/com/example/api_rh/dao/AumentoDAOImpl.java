package com.example.api_rh.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public class AumentoDAOImpl implements AumentoDAO {

    private final JdbcTemplate jdbcTemplate;

    public AumentoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int exec(double percentual) {
        String sql = "update tb_funcionario set fun_salario = fun_salario + (fun_salario * ? / 100.0)" +
                " where fun_datademissao is null";

        return jdbcTemplate.update(sql, percentual);
    }

    @Override
    public void save(OffsetDateTime data, double percentual, Long usuarioId) {
        String sql = "insert into tb_aumentosalario (aus_data, aus_percentual, usu_id) values (?, ?, ?)";

        jdbcTemplate.update(sql, data, percentual, usuarioId);
    }
}
