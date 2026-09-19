package com.example.api_rh.services;

import com.example.api_rh.dao.CargoDAO;
import com.example.api_rh.dao.CargoDAOImpl;
import com.example.api_rh.dao.FuncionarioDAO;
import com.example.api_rh.dao.FuncionarioDAOImpl;
import com.example.api_rh.dto.request.FuncionarioRequestDTO;
import com.example.api_rh.dto.request.FuncionarioResponseDTO;
import com.example.api_rh.entity.Cargo;
import com.example.api_rh.entity.Funcionario;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class FuncionarioService {
    private final FuncionarioDAO funcionarioDAO;
    private final CargoDAO cargoDAO;

    public FuncionarioService(FuncionarioDAO funcionarioDAO, CargoDAO cargoDAO) {
        this.funcionarioDAO = funcionarioDAO;
        this.cargoDAO = cargoDAO;
    }

    public FuncionarioResponseDTO save(FuncionarioRequestDTO dto) {
        try {
            Cargo cargo = cargoDAO.findById(dto.cargo());

            Funcionario funcionario = new Funcionario();

            funcionario.setNome(dto.nome());
            funcionario.setCpf(dto.cpf());
            funcionario.setCargoId(cargo);
            funcionario.setDatAdmissao(OffsetDateTime.now());
            funcionario.setSalario(dto.salario());

            Funcionario savedFuncionario = funcionarioDAO.save(funcionario);

            return FuncionarioResponseDTO.fromEntity(savedFuncionario);
        }
        catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Cargo não encontrado para o ID: " + dto.cargo());
        }
    }

    public void delete(Long id) {
        try {
            Funcionario funcionario = funcionarioDAO.findById(id);
            funcionario.setDateDemissao(OffsetDateTime.now());

            funcionarioDAO.delete(funcionario.getId());
        }
        catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Funcionário não encontrado para o ID: " + id);
        }
    }
}
