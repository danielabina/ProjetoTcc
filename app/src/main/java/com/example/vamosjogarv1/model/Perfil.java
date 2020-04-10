package com.example.vamosjogarv1.model;


/**
 * Classe de atributos PERFIL
 * @author : Daniela Bina
 * @date : 10/04/2020
 */
public class Perfil {

    public int idPerfil;
    public String descricao;
    public String avaliacao;
    //relacao pessoa

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }



}
