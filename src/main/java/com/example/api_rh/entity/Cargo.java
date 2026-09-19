package com.example.api_rh.entity;

public class Cargo {

    private Long id;

    private String descricao;

    public Cargo(String descricao, Long id) {
        this.descricao = descricao;
        this.id = id;
    }

    public Cargo() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
