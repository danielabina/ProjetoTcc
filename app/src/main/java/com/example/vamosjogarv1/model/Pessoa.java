package com.example.vamosjogarv1.model;


/**
 * Classe de atributos PESSOA
 * @author : Daniela Bina
 * @date : 10/04/2020
 */
public class Pessoa {
    public int idPessoa;
    public String nome;
    public String email;
    public int telefone;
    public double contaPagamento;
    // incuir endereço e logo


    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public double getContaPagamento() {
        return contaPagamento;
    }

    public void setContaPagamento(double contaPagamento) {
        this.contaPagamento = contaPagamento;
    }
}

