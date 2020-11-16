package com.example.vamosjogarv1.model;

public class Avaliacao {

    int idAvaliacao;
    int idPessoa;
    int idEvento;
    int idAvaliado;
    String comentario;
    String nomeEvento;
    int valorEstrela;

    public Avaliacao(int idAvaliacao, String comentario, int valorEstrela,String nomeEvento) {
        this.idAvaliacao = idAvaliacao;
        this.comentario = comentario;
        this.valorEstrela = valorEstrela;
        this.nomeEvento = nomeEvento;
    }


    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdAvaliado() {
        return idAvaliado;
    }

    public void setIdAvaliado(int idAvaliado) {
        this.idAvaliado = idAvaliado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public int getValorEstrela() {
        return valorEstrela;
    }

    public void setValorEstrela(int valorEstrela) {
        this.valorEstrela = valorEstrela;
    }

}
