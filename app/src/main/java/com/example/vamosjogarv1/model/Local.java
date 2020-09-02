package com.example.vamosjogarv1.model;

public class Local {

    private int id;
    private String nome;
    private String categoria;
    private String endereco;
    private String valor;
    private String descricao;


    public Local(int id, String nome, String descricao,String endereco,String categoria,String valor){

        this.setId(id);
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setEndereco(endereco);
        this.setCategoria(categoria);
        this.setValor(valor);


    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Local() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDescricao(String id) {
        this.descricao = descricao;
    }

}
