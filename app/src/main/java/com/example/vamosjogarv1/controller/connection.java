package com.example.vamosjogarv1.controller;

public class connection {

    String Login;
    String BuscaLocal;

    public String getLogin() {
        return Login = "http://192.168.1.109/Login/logar.php";
    }
    public String getBuscaLocal() {
        return BuscaLocal = "http://192.168.1.109/Controller/APIListarLocal.php";
    }

    public void setLogin(String login) {
        Login = login;
    }
    public void setBuscaLocal(String busca) {
        BuscaLocal = busca;
    }
}
