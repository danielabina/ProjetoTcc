package com.example.vamosjogarv1.model;


import android.widget.EditText;

/**
 * Classe de atributos PESSOA
 * @author : Daniela Bina
 * @date : 10/04/2020
 */
public class Pessoa {
    public int idPessoa;
    public String nome;
    public String email;
    public String habilidade;
    public String senha;
    public String sexo;
    // incuir endereço e logo

    public Pessoa(int idPessoa, String nome, String email,String senha,String habilidade,String sexo){

        this.setIdPessoa(idPessoa);
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setHabilidade(habilidade);
        this.setSexo(sexo);


    }

    public Pessoa(int idPessoa, String nome,String senha,String habilidade,String sexo){

        this.setIdPessoa(idPessoa);
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setHabilidade(habilidade);
        this.setSexo(sexo);


    }

    public Pessoa(String nome,String habilidade,String sexo){
        this.setNome(nome);
        this.setHabilidade(habilidade);
        this.setSexo(sexo);
    }
    public Pessoa(int idPessoa,String nome,String habilidade,String sexo){
        this.setNome(nome);
        this.setHabilidade(habilidade);
        this.setSexo(sexo);
        this.setIdPessoa(idPessoa);
    }

    public Pessoa(){ }
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }


    public String getHabilidade() {
        return habilidade;
    }
    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

