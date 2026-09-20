package com.example.api_rh.entity;

public class Usuario {
    private long id;

    private String nome;

    private String email;

    private String senha;

    private String ativo;

    public Usuario(long id, String nome, String email, String senha, String ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Usuario() {
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
