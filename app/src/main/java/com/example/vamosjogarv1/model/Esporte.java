package com.example.vamosjogarv1.model;

/**
 * Classe de atributos ESPORTE
 * @author : Daniela Bina
 * @date : 10/04/2020
 */
public class Esporte {

    public int idEsporte;
    public String nome;
    public String quantidadeJogador;

    public int getIdEsporte() {
        return idEsporte;
    }

    public void setIdEsporte(int idEsporte) {
        this.idEsporte = idEsporte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidadeJogador() {
        return quantidadeJogador;
    }

    public void setQuantidadeJogador(String quantidadeJogador) {
        this.quantidadeJogador = quantidadeJogador;
    }
}
