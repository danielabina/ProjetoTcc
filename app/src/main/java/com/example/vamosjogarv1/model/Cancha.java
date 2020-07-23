package com.example.vamosjogarv1.model;

import java.util.List;
/**
 * Classe de atributos CANCHA
 * @author : Daniela Bina
 * @date : 10/04/2020
 */
public class Cancha {

    public int idCancha;
    public String termoCondicoes;
    public double valorHora;
    //opcional



    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public String getTermoCondicoes() {
        return termoCondicoes;
    }

    public void setTermoCondicoes(String termoCondicoes) {
        this.termoCondicoes = termoCondicoes;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }
}
