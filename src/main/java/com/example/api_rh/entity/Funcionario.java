package com.example.api_rh.entity;


import java.time.OffsetDateTime;

public class Funcionario {

    private Long id;

    private String nome;

    private String cpf;

    private double salario;

    private Cargo cargoId;

    private OffsetDateTime datAdmissao;

    private OffsetDateTime dateDemissao;

    public Funcionario(Cargo cargoId, String cpf, OffsetDateTime datAdmissao, OffsetDateTime dateDemissao, Long id, String nome, double salario) {
        this.cargoId = cargoId;
        this.cpf = cpf;
        this.datAdmissao = datAdmissao;
        this.dateDemissao = dateDemissao;
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }

    public Funcionario() {
    }

    public Cargo getCargoId() {
        return cargoId;
    }

    public void setCargoId(Cargo cargoId) {
        this.cargoId = cargoId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public OffsetDateTime getDatAdmissao() {
        return datAdmissao;
    }

    public void setDatAdmissao(OffsetDateTime datAdmissao) {
        this.datAdmissao = datAdmissao;
    }

    public OffsetDateTime getDateDemissao() {
        return dateDemissao;
    }

    public void setDateDemissao(OffsetDateTime dateDemissao) {
        this.dateDemissao = dateDemissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
