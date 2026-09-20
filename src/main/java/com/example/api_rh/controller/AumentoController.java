package com.example.api_rh.controller;

import com.example.api_rh.dto.request.AumentoRequestDTO;
import com.example.api_rh.services.AumentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aumento")
public class AumentoController {

    private final AumentoService aumentoService;

    public AumentoController(AumentoService aumentoService) {
        this.aumentoService = aumentoService;
    }

    @PostMapping
    public ResponseEntity<?> execAumento(@RequestBody AumentoRequestDTO aumento) {
        try {
            aumentoService.aplicarAumento(aumento.percentual());
            return ResponseEntity.status(200).body("aumento aplicado");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
