package com.example.vamosjogarv1.model;


/**
 * Classe de atributos EVENTO
 * @author : Daniela Bina
 * @date : 10/04/2020
 */
public class Evento {

    public int idEvento;
    public int idCancha;
    public String idEventoo;
    public int idPessoa;
    public String dataHoraa;
    private String nomeEvento;
    public String endereco;
    public String categoria;

    public Evento(int idEvento, String dataHora, String nomeEvento, String modalidade) {
    }

    public String getDataHoraa() {
        return dataHoraa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }



    public Evento(int idCancha, String dataHoraa, String nomeEvento) {
        this.idCancha = idCancha;
        this.dataHoraa = dataHoraa;
        this.nomeEvento = nomeEvento;
    }

    public Evento(int idCancha, String dataHoraa, String nomeEvento,String modalidade,String endereco) {
        this.idCancha = idCancha;
        this.dataHoraa = dataHoraa;
        this.nomeEvento = nomeEvento;
        this.categoria = modalidade;
        this.endereco = endereco;
    }

    public Evento(String idEventoo, String dataHoraa, String nomeEvento,String modalidade,String endereco) {
        this.idEventoo = idEventoo;
        this.dataHoraa = dataHoraa;
        this.nomeEvento = nomeEvento;
        this.categoria = modalidade;
        this.endereco = endereco;
    }

    public Evento(int idEvento, int idCancha,int idPessoa){
        this.idEvento = idEvento;
        this.idCancha = idCancha;
        this.idPessoa = idPessoa;
    }

    public Evento(int idCancha, String nomeEvento,String dataHoraa, String endereco,String categoria,int idEvento) {
        this.idCancha = idCancha;
        this.dataHoraa = dataHoraa;
        this.nomeEvento = nomeEvento;
        this.endereco = endereco;
        this.dataHoraa = dataHoraa;
        this.categoria = categoria;
        this.idEvento = idEvento;


    }


    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdEventoo() {
        return idEventoo;
    }

    public void setIdEventoo(String idEventoo) {
        this.idEventoo = idEventoo;
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
