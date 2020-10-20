package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Pessoa;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class tela_avaliar extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView txtValorAvaliacao;
    CadastrarAvaliacaoAsyncTask cadastrarAvaliacaoAsyncTask;
    connection con = new connection();
    int controlador,idPessoaAvaliado;
    String idPessoa,idEvento;
    String comentarioAva;
    String valor;
    EditText comentario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avaliar);
        addListenerOnRatingBar();
        addListenerOnButton();
        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getString("IDPESSOALOGIN");
        idPessoaAvaliado = extras.getInt("IDPESSOAAVALIACAO");
        idEvento = extras.getString("IDEVENTO");
        comentario = (EditText) findViewById(R.id.txtdescricaoAvaliacao);
        comentarioAva = comentario.getText().toString();
    }

    public void addListenerOnRatingBar() {
         ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtValorAvaliacao = (TextView) findViewById(R.id.txtValorAvaliacao);

        //se o valor de avaliação mudar,
        //exiba o valor de avaliação atual no resultado (textview) automaticamente
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float avaliacao, boolean fromUser) {
                txtValorAvaliacao.setText(String.valueOf(avaliacao));
            }
        });
    }

    public void addListenerOnButton() {
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //se o botão for clicado, exiba o valor de avaliação corrente.
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comentarioAva = comentario.getText().toString();
                valor = String.valueOf(ratingBar.getRating());
                cadastrarAvaliacaoAsyncTask = new CadastrarAvaliacaoAsyncTask();
                cadastrarAvaliacaoAsyncTask.execute();
            }
        });
    }

    public class CadastrarAvaliacaoAsyncTask extends AsyncTask<String, String, String> {

        String  query;
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;
        final String URL_WEB_SERVICES = con.getUrlCadastrarAvaliacao();
        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;
        int response_code;

        public CadastrarAvaliacaoAsyncTask( ){
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_idPessoa", String.valueOf(idPessoa));
            builder.appendQueryParameter("api_idAvaliado", String.valueOf(idPessoaAvaliado));
            builder.appendQueryParameter("api_idEvento", String.valueOf(idEvento));
            builder.appendQueryParameter("api_comentario", comentarioAva);
            builder.appendQueryParameter("api_valorEstrela", valor);
        }

        @Override
        protected void onPreExecute() {
            Log.i("APIListar", "onPreExecute()");
        }
        @Override
        protected String doInBackground(String... strings) {
            Log.i("APIListar", "doInBackground()");
            try {
                url = new URL(URL_WEB_SERVICES);
            } catch (MalformedURLException e) {
                Log.i("APIListar", "MalformedURLException --> " + e.getMessage());
            } catch (Exception e) {
                Log.i("APIListar", "doInBackground() --> " + e.getMessage());
            }
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

                if(result.equals("SUCESSO")){
                    controlador=1;
                }else if(result.equals("JA EXISTE")){
                    controlador=2;
                }else{
                    controlador=0;
                }

                if(controlador == 1){
                    Toast.makeText(tela_avaliar.this, "Avaliação realizada com sucesso", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(tela_avaliar.this, tela_lista_avaliacao.class);
                    it.putExtra("IDPESSOA", idPessoa);
                    it.putExtra("IDEVENTO", idEvento);
                    startActivity(it);
                }else if(controlador == 2){
                    Toast.makeText(tela_avaliar.this, "Ja existe um comentario seu nesse evento para esse jogador/colega! " +
                            "", Toast.LENGTH_LONG).show();
                }else if(controlador == 0){
                    Toast.makeText(tela_avaliar.this, "Algo deu errado tente novamente " +
                            "", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }
    }
}
