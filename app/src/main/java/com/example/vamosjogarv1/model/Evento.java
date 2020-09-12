package com.example.vamosjogarv1.model;

import java.sql.Date;


/**
 * Classe de atributos EVENTO
 * @author : Daniela Bina
 * @date : 10/04/2020
 */
public class Evento {

    public int idEvento;
    public String idCancha;
    public String idPessoa;
    public String dataHoraa;
    private String nomeEvento;


    public void setDataHoraa(String dataHoraa) {
        this.dataHoraa = dataHoraa;
    }

    public Evento(String nome, String descricao, Categoria categoria) {
        this.nomeEvento = nomeEvento;
        this.categoria = categoria;
    }

    public Evento(String idCancha, String idPessoa, String dataHoraa, String nomeEvento) {
        this.nomeEvento = nomeEvento;
        this.idCancha = idCancha;
        this.idPessoa = idPessoa;
        this.dataHoraa = dataHoraa;
        this.nomeEvento = nomeEvento;


    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    private Categoria categoria;

    //verificarRelacaoOutras classes


    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(String idCancha) {
        this.idCancha = idCancha;
    }

    public String getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
