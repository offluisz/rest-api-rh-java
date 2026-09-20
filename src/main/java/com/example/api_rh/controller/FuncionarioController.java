package com.example.api_rh.controller;

import com.example.api_rh.dto.request.FuncionarioRequestDTO;
import com.example.api_rh.dto.response.FuncionarioResponseDTO;
import com.example.api_rh.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid FuncionarioRequestDTO funcionario) {
        try {
            FuncionarioResponseDTO saveFuncionario = funcionarioService.save(funcionario);

            return ResponseEntity.status(201).body(saveFuncionario);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao cadastrar funcionário.");
        }
    }

    @PatchMapping("/{id}/demitir")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            funcionarioService.delete(id);

            return ResponseEntity.status(200).build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao demitir funcionário.");
        }
    }
}
