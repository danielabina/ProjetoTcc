package com.example.vamosjogarv1.controller;

public class connection {

    String Login;
    String BuscaLocal;
    String BuscaLocalDetalhe;
    String IP = "192.168.1.110";

    public String getLogin() {

        return Login = "http://"+IP+"/Login/logar.php";
    }
    public String getBuscaLocal() { return BuscaLocal = "http://"+IP+"/Controller/APIListarLocalDisp.php";
    }
    public String getBuscaLocalDetalhe() { return BuscaLocal = "http://"+IP+"/Controller/APIListarLocalDetalhe.php";
    }

}
