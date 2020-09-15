package com.example.vamosjogarv1.model;


/**
 * Classe de atributos EVENTO
 * @author : Daniela Bina
 * @date : 10/04/2020
 */
public class Evento {

    public int idEvento;
    public int idCancha;
    public String dataHoraa;
    private String nomeEvento;
    public String endereco;
    public String categoria;

    public String getDataHoraa() {
        return dataHoraa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDataHoraa(String dataHoraa) {
        this.dataHoraa = dataHoraa;
    }

    public Evento(int idCancha, String dataHoraa, String nomeEvento) {
        this.idCancha = idCancha;
        this.dataHoraa = dataHoraa;
        this.nomeEvento = nomeEvento;
    }

    public Evento(int idCancha, String dataHoraa, String nomeEvento,String endereco,String categoria) {
        this.idCancha = idCancha;
        this.dataHoraa = dataHoraa;
        this.nomeEvento = nomeEvento;
        this.endereco = endereco;
        this.dataHoraa = dataHoraa;
        this.categoria = categoria;


    }


    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
