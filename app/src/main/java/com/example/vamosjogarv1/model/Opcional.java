package com.example.vamosjogarv1.model;

/**
 * Classe de atributos OPCIONAL
 * @author : Daniela Bina
 * @date : 10/04/2020
 */
public class Opcional {

    public int idOpcional;
    public String descricao;
    public int quantidadeItem;
    public double valorItem;

    public int getIdOpcional() {
        return idOpcional;
    }

    public void setIdOpcional(int idOpcional) {
        this.idOpcional = idOpcional;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(int quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }
}
