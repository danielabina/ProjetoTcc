package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.AdapterLocalPersonalizado;
import com.example.vamosjogarv1.model.Local;
import com.example.vamosjogarv1.model.Pessoa;
import com.squareup.picasso.Picasso;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class tela_detalhe_local extends AppCompatActivity {
    Button btnproximook;
    TextView txtNomeLocal,txtEnderecoLocal,txtDescricaoLocal,txtValor;
    int idLocal;
    String idPessoa,nomeEvento,dataHora,foto;
    private Bundle extra;
    connection con = new connection();
    ListarLocaisDetalheAsyncTask listarLocaisDetalheAsyncTask;
    List<Local> localList;
    Local local;
    Pessoa pessoa;
    ImageView imagemLocal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_local);
        btnproximook = (Button) findViewById(R.id.btnproximook);
        txtNomeLocal = (TextView) findViewById(R.id.nomeLocal);
        txtEnderecoLocal = (TextView)  findViewById(R.id.enderecolocal);
        txtDescricaoLocal = (TextView) findViewById(R.id.descricaolocal);
        txtValor = (TextView) findViewById(R.id.valorLocal);
        imagemLocal = (ImageView) findViewById(R.id.imagemLocal);




        Bundle it = getIntent().getExtras();
        idLocal= it.getInt("idLocal");
        idPessoa = it.getString("IDPESSOA");
        nomeEvento = it.getString("nomeEvento");
        dataHora = it.getString("dataHora");
        foto = it.getString("FOTO");
        CarregarImagem();
        listarLocaisDetalheAsyncTask = new ListarLocaisDetalheAsyncTask();
        listarLocaisDetalheAsyncTask.execute();


        btnproximook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherEvento();
            }
        });
    }

    public void CarregarImagem() {

        Picasso
                .with(this)
                .load(foto)
                .placeholder(R.drawable.carregando_animacao)
                .into(imagemLocal);
    }

    public class ListarLocaisDetalheAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = con.getBuscaLocalDetalhe();

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public ListarLocaisDetalheAsyncTask( ){

            this.builder = new Uri.Builder();

            builder.appendQueryParameter("api_idLocal", Integer.toString(idLocal));

        }

        @Override
        protected void onPreExecute() {

            Log.i("APIListar", "onPreExecute()");

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i("APIListar", "doInBackground()");

            // Gerar o conteúdo para a URL

            try {

                url = new URL(URL_WEB_SERVICES);

            } catch (MalformedURLException e) {

                Log.i("APIListar", "MalformedURLException --> " + e.getMessage());

            } catch (Exception e) {

                Log.i("APIListar", "doInBackground() --> " + e.getMessage());
            }

            // Gerar uma requisição HTTP - POST - Result será um ArrayJson

            // conn

            try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();

            } catch (Exception e) {

                Log.i("APIListar", "HttpURLConnection --> " + e.getMessage());

            }

            // Adicionar o TOKEN e/ou outros parâmetros como por exemplo
            // um objeto a ser incluido, deletado ou alterado.
            // CRUD completo

            try {

                query = builder.build().getEncodedQuery();

                OutputStream stream = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(stream, "utf-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                stream.close();

                conn.connect();


            } catch (Exception e) {

                Log.i("APIListar", "BufferedWriter --> " + e.getMessage());


            }

            // receber o response - arrayJson
            // http - código do response | 200 | 404 | 503

            try {

                response_code = conn.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {


                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    StringBuilder result = new StringBuilder();

                    String linha = null;

                    while ((linha = reader.readLine()) != null) {

                        result.append(linha);
                    }

                    return result.toString();

                } else {

                    return "HTTP ERRO: " + response_code;
                }


            } catch (Exception e) {

                Log.i("APIListar", "StringBuilder --> " + e.getMessage());

                return "Exception Erro: " + e.getMessage();

            } finally {

                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("APIListar", "onPostExecute()--> Result: " + result);

            try {



                JSONArray jsonArray = new JSONArray(result);

                localList = new ArrayList<>();

                if (jsonArray.length() != 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        local = new Local(jsonObject.getInt("id"),
                                jsonObject.getString("nome"),
                                jsonObject.getString("descricao"),
                                jsonObject.getString("endereco"),
                                jsonObject.getString("categoria"),
                                jsonObject.getString("valor"));

                        localList.add(local);

                        Log.i("APIListar", "detalhe: -> " + local.getId() + " - " +local.getNome());


                    }

                    Toast.makeText(tela_detalhe_local.this, localList.size() + " local Listados no LogCat", Toast.LENGTH_LONG)
                            .show();

                    initial(local);

                }

            } catch (Exception e) {


                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());


            }


        }

  public void initial(Local local){
           txtNomeLocal.setText(local.getNome());
           txtDescricaoLocal.setText(local.getDescricao());
           txtEnderecoLocal.setText(local.getEndereco());
           txtValor.setText(local.getValor());
       }
    }





    public void escolherEvento(){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Confirmar Local");
        msgBox.setIcon(android.R.drawable.ic_menu_add);
        msgBox.setMessage("Tem Certeza que deseja escolher esse local para o evento?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_detalhe_local.this,"Muito bem! estamos ansiosos pelo dia!",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_detalhe_local.this, tela_metodo_pagamento.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID",idLocal);
                it.putExtra("IDPESSOA",idPessoa);
                it.putExtra("nomeEvento",nomeEvento);
                it.putExtra("dataHora",dataHora);
                it.putExtra("ID",idLocal);

                bundle.putString("VALOR",local.getValor());
                bundle.putString("IDPESSOA",idPessoa);
                bundle.putString("nomeEvento",nomeEvento);
                bundle.putString("dataHora",dataHora);

                startActivity(it);
                finish();
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_detalhe_local.this,"Tudo bem,temos varias opções!",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_detalhe_local.this, tela_detalhe_local.class);
                startActivity(it);
            }
        });
        msgBox.show();


    }
}
