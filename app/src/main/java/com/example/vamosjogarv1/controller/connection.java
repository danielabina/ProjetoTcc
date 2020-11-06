package com.example.vamosjogarv1.controller;

public class connection {

    String Login;
    String BuscaLocal;
    String BuscaLocalDetalhe;
    String BuscaIdPessoa;
    String Cadastrar;
    String UrlPagamento;
    String UrlCadastrarEvento;
    String UrlEditarDados;
    String UrlBuscarEvento;
    String UrlInserirParticipanteEvento;
    String UrlBuscarParticipantesEvento;
    String UrlMeusEventosAnteriores;
    String UrlMeusEventosProximos;
    String UrlCancelarParticipacao;
    String UrlCadastrarAvaliacao;
    String UrlBuscarAvaliacao;
    String upload;
    String IP = "192.168.1.80";

    public String getLogin() { return Login = "http://"+IP+"/Login/logar.php"; }
    public String getBuscaLocal() { return BuscaLocal = "http://"+IP+"/Controller/APIListarLocalDisp.php";}
    public String getBuscaLocalDetalhe() { return BuscaLocalDetalhe = "http://"+IP+"/Controller/APIListarLocalDetalhe.php"; }
    public String getCadastrar() { return Cadastrar = "http://"+IP+"/Login/cadastrar.php"; }
    public String getBuscaIdPessoa() { return BuscaIdPessoa = "http://"+IP+"/Controller/APIBuscaIdPessoa.php";}
    public String getUrlPagamento() { return UrlPagamento = "http://"+IP+"/Controller/android-payment/";}
    public String getUrlCadastrarEvento() { return UrlCadastrarEvento = "http://"+IP+"/Controller/cadastrarEvento.php";}
    public String getUrlEditarDados() {return UrlEditarDados = "http://"+IP+"/Controller/editarDadosPerfil.php";}
    public String getBuscaEvento() {return UrlBuscarEvento = "http://"+IP+"/Controller/buscarEventos.php";}
    public String getInserirParticipanteEvento(){return UrlInserirParticipanteEvento = "http://"+IP+"/Controller/inserirParticipanteEvento.php";}
    public String getUrlBuscarParticipantesEvento(){return UrlBuscarParticipantesEvento = "http://"+IP+"/Controller/buscarParticipantesEvento.php";}
    public String getMeusEventosAnteriores(){return UrlMeusEventosAnteriores = "http://"+IP+"/Controller/buscarMeusEventosAnteriores.php";}
    public String getMeusProximosEventos(){return UrlMeusEventosProximos = "http://"+IP+"/Controller/buscarMeusProximosEventos.php";}
    public String getCancelarParticipacao(){return UrlCancelarParticipacao = "http://"+IP+"/Controller/cancelarParticipacao.php";}
    public String getUrlCadastrarAvaliacao(){return UrlCadastrarAvaliacao = "http://"+IP+"/Controller/cadastrarAvaliacao.php";}
    public String getUrlBuscarAvaliacao() {return UrlBuscarAvaliacao = "http://"+IP+"/Controller/buscarAvaliacao.php";}
    public String getUpdate() {return upload = "http://"+IP+"/Controller/upload.php";}

}
