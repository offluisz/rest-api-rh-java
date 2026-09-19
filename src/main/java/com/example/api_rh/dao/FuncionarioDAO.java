package com.example.api_rh.dao;

import com.example.api_rh.entity.Funcionario;

public interface FuncionarioDAO {

    Funcionario save(Funcionario funcionario);

    Funcionario findById(Long id);

    Funcionario delete(Long id);
}
