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




    public void setDataHoraa(String dataHoraa) {
        this.dataHoraa = dataHoraa;
    }


    public Evento(int idCancha, String dataHoraa, String nomeEvento) {
        this.idCancha = idCancha;
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
