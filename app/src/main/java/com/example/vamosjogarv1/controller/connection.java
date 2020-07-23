package com.example.vamosjogarv1.controller;

public class connection {

    String Login;
    String Busca;

    public String getLogin() {
        return Login = "http://192.168.0.109/Login/logar.php";
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getBusca() {
        return Busca = "http://192.168.0.109/Controller/BuscarLocal.php";
    }



    public void setBusca(String busca) {
        Busca = busca;
    }
}
