package com.example.api_rh.entity;

import java.time.OffsetDateTime;

public class Aumento {

    private Long id;

    private OffsetDateTime data;

    private Double percentual;

    private Usuario usuarioId;

    public Aumento(Long id, OffsetDateTime data, Double percentual, Usuario usuarioId) {
        this.id = id;
        this.data = data;
        this.percentual = percentual;
        this.usuarioId = usuarioId;
    }

    public Aumento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getData() {
        return data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }
}
