package com.example.t1progmobile.entities;

public class Pessoa {

    private int pessoaId,vagaId,senha;
    private String nome, cpf, email, telefone;

    public Pessoa() {}

    public Pessoa(String nome, String cpf, String email, String telefone, int senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Pessoa(int pessoaId, String nome, String cpf, String email, String telefone, int senha) {
        this.pessoaId = pessoaId;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public int getVagaId() {
        return vagaId;
    }


    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setId(int id) {
        this.pessoaId = id;
    }
}
