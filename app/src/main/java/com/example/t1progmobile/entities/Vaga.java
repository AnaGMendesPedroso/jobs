package com.example.t1progmobile.entities;

public class Vaga {

    private int vagaId, horasSemana;

    private String descricao;

    private double valor;


    public Vaga(){}

    public Vaga(int id , String descricao, int horasSemana, double valor){
        this.vagaId = id;
        this.descricao = descricao;
        this.horasSemana = horasSemana;
        this.valor = valor;
    }

    public int getVagaId() {
        return vagaId;
    }

    public int getHorasSemana() {
        return horasSemana;
    }

    public void setHorasSemana(int horasSemana) {
        this.horasSemana = horasSemana;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
