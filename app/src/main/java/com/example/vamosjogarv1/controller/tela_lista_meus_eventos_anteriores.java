package com.example.vamosjogarv1.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vamosjogarv1.R;
//import com.example.vamosjogarv1.model.AdapterEventosAnterioresPersonalizado;
//import com.example.vamosjogarv1.model.AdapterEventosPersonalizado;
import com.example.vamosjogarv1.model.AdapterEventosAnterioresPersonalizado;
import com.example.vamosjogarv1.model.AdapterEventosPersonalizado;
import com.example.vamosjogarv1.model.Evento;

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
import java.util.ArrayList;
import java.util.List;

public class tela_lista_meus_eventos_anteriores extends AppCompatActivity {
    ListaEventosAnteriores listaEventosAnteriores;
    connection con = new connection();
    List<Evento> eventoList;
    RecyclerView listView;
    AdapterEventosAnterioresPersonalizado adapterEventosAnterioresPersonalizado;
    int idPessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_eventos_anteriores);
        listView = findViewById(R.id.listaAnterioresEventos);
        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getInt("IDPESSOA");
        listaEventosAnteriores = new ListaEventosAnteriores();
        listaEventosAnteriores.execute();
    }

    public class ListaEventosAnteriores extends AsyncTask<String, String, String> {
        String api_token, query;
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = con.getMeusEventosAnteriores();
        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;
        int response_code;
        public ListaEventosAnteriores(){
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_idPessoa", String.valueOf(idPessoa));
        }

        @Override
        protected void onPreExecute() {
            Log.i("APIListar", "onPreExecute()");
        }

        @Override
        protected String doInBackground(String... strings) {

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
            Log.i("APIListarEventoderalhe", "onPostExecute()--> Result: " + result);
            try {
                Evento evento;
                JSONArray jsonArray = new JSONArray(result);
                eventoList = new ArrayList<>();
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        evento = new  Evento(jsonObject.getString("idEvento"),
                                jsonObject.getString("dataHora"),
                                jsonObject.getString("nomeEvento"),
                                jsonObject.getString("categoria"),
                                jsonObject.getString("endereco"));
                        eventoList.add(evento);
                        Log.i("APIListar", "Estado: -> " + evento.getIdCancha() + " - " +evento.getNomeEvento());
                    }
                }
                initial();
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }
    }

    public void initial(){
        adapterEventosAnterioresPersonalizado = new AdapterEventosAnterioresPersonalizado(eventoList,getApplicationContext(),String.valueOf(idPessoa));
        listView.setAdapter(adapterEventosAnterioresPersonalizado);
        listView.setLayoutManager(new LinearLayoutManager(tela_lista_meus_eventos_anteriores.this));
    }
}

