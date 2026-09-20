package com.example.api_rh.dao;

import com.example.api_rh.entity.Cargo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CargoDAOImpl implements CargoDAO{

    private final JdbcTemplate jdbcTemplate;


    public CargoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Cargo findById(Long id) {
        String sql = "select * from tb_cargo where car_id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Cargo cargo = new Cargo();
            cargo.setId(rs.getLong("car_id"));

            return cargo;
        }, id);
    }
}
