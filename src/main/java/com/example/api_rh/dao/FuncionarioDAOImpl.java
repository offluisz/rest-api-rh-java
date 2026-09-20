package com.example.api_rh.dao;

import com.example.api_rh.entity.Funcionario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.OffsetDateTime;

@Repository
public class FuncionarioDAOImpl implements FuncionarioDAO {

    private final JdbcTemplate jdbcTemplate;

    public FuncionarioDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        String sql = "insert into tb_funcionario(fun_nome, fun_cpf, fun_salario, fun_datadmissao, fun_datademissao, car_id) " +
                "values (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setDouble(3, funcionario.getSalario());
            ps.setObject(4, funcionario.getDatAdmissao());
            ps.setObject(5, funcionario.getDateDemissao());
            ps.setLong(6, funcionario.getCargoId().getId());
            return ps;
        }, keyHolder);

        funcionario.setId(keyHolder.getKey().longValue());

        return funcionario;
    }

    @Override
    public Funcionario findById(Long id) {
        String sql = "select * from tb_funcionario where fun_id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(rs.getLong("fun_id"));
            funcionario.setNome(rs.getString("fun_nome"));
            funcionario.setCpf(rs.getString("fun_cpf"));
            funcionario.setSalario(rs.getDouble("fun_salario"));
            funcionario.setDatAdmissao(rs.getObject("fun_datadmissao", java.time.OffsetDateTime.class));
            funcionario.setDateDemissao(rs.getObject("fun_datademissao", java.time.OffsetDateTime.class));

            CargoDAOImpl cargoDAO = new CargoDAOImpl(jdbcTemplate);
            funcionario.setCargoId(cargoDAO.findById(rs.getLong("car_id")));

            return funcionario;
        }, id);
    }

    @Override
    public void delete(Long id) {
        String sql = "update tb_funcionario set fun_datademissao = ? where fun_id = ?";

        jdbcTemplate.update(sql, OffsetDateTime.now(), id);

        findById(id);
    }
}
