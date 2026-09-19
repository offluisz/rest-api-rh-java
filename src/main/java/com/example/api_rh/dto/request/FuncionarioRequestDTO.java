package com.example.api_rh.dto.request;

import com.example.api_rh.entity.Funcionario;

import java.time.OffsetDateTime;

public record FuncionarioRequestDTO(
        String nome,
        String cpf,
        Long cargo,
        Double salario) {
}
