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
    public String senha;
    public String sexo;
    // incuir endereço e logo


    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getSenha() {
        return senha;
    }
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


    public void setEmail(EditText viewById) {
    }

    public void setSenha(EditText viewById) {
    }


}

