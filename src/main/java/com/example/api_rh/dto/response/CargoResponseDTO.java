package com.example.api_rh.dto.response;

public record CargoResponseDTO(
        Long Id
) {
    public static CargoResponseDTO fromEntity(Long id) {
        return new CargoResponseDTO(id);
    }
}
