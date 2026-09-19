package com.example.api_rh.dto.request;

import com.example.api_rh.entity.Funcionario;

import java.time.OffsetDateTime;

public record FuncionarioResponseDTO (
        Long id,
        String nome,
        String cpf,
        double salario,
        OffsetDateTime datAdmissao,
        CargoResponseDTO cargo
) {
    public static FuncionarioResponseDTO fromEntity(Long id, String nome, String cpf, double salario, OffsetDateTime datAdmissao, CargoResponseDTO cargo) {
        return new FuncionarioResponseDTO(id, nome, cpf, salario, datAdmissao, cargo);
    }

    public static FuncionarioResponseDTO fromEntity(Funcionario savedFuncionario) {
        return new FuncionarioResponseDTO(
                savedFuncionario.getId(),
                savedFuncionario.getNome(),
                savedFuncionario.getCpf(),
                savedFuncionario.getSalario(),
                savedFuncionario.getDatAdmissao(),
                CargoResponseDTO.fromEntity(savedFuncionario.getCargoId().getId())
        );
    }
}
