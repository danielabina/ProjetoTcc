package com.example.vamosjogarv1.controller;

public class connection {

    String Login;
    String BuscaLocal;
    String BuscaLocalDetalhe;
    String Cadastrar;
    String IP = "192.168.15.4";

    public String getLogin() {

        return Login = "http://"+IP+"/Login/logar.php";
    }
    public String getBuscaLocal() { return BuscaLocal = "http://"+IP+"/Controller/APIListarLocalDisp.php";
    }
    public String getBuscaLocalDetalhe() { return BuscaLocal = "http://"+IP+"/Controller/APIListarLocalDetalhe.php";
    }
    public String getCadastrar() { return Cadastrar = "http://"+IP+"/Login/cadastrar.php";
    }

}
