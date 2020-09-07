package com.example.vamosjogarv1.controller;

public class connection {

    String Login;
    String BuscaLocal;
    String BuscaLocalDetalhe;
    String BuscaIdPessoa;
    String Cadastrar;
    String IP = "192.168.0.114";

    public String getLogin() {

        return Login = "http://"+IP+"/Login/logar.php";
    }
    public String getBuscaLocal() { return BuscaLocal = "http://"+IP+"/Controller/APIListarLocalDisp.php";
    }
    public String getBuscaLocalDetalhe() { return BuscaLocal = "http://"+IP+"/Controller/APIListarLocalDetalhe.php";
    }
    public String getCadastrar() { return Cadastrar = "http://"+IP+"/Login/cadastrar.php";
    }
    public String getBuscaIdPessoa() { return Cadastrar = "http://"+IP+"/Controller/APIBuscaIdPessoa.php";
    }

}
