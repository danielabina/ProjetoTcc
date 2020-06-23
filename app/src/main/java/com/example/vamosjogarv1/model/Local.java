package com.example.vamosjogarv1.model;

public class Local {

    private String nome;
    private String descricao;
    private Categoria categoria;

    public Local(String nome, String descricao, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;

        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Curso: " + nome + " Descrição: " +
                descricao ;
    }
}
