package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vamosjogarv1.R;

import com.example.vamosjogarv1.model.AdapterEventosPersonalizado;
import com.example.vamosjogarv1.model.AdapterLocalPersonalizado;
import com.example.vamosjogarv1.model.Categoria;
import com.example.vamosjogarv1.model.Evento;
import com.example.vamosjogarv1.model.Local;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tela_lista_todos_eventos extends AppCompatActivity {
    AdapterEventosPersonalizado adapterEventosPersonalizado;
    List<Evento> eventoList;
    Evento evento;
    String endereco,categoria,idpessoa;

    connection con = new connection();
    ListarEventosAsyncTask listarEventosAsyncTask;
    RecyclerView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_todos_eventos);

        Intent it = getIntent();
        categoria = it.getStringExtra("categoria");
        endereco = it.getStringExtra("endereco");
        idpessoa = it.getStringExtra("IDPESSOA");
        listView = findViewById(R.id.recyclerViewEvento);
        listarEventosAsyncTask = new ListarEventosAsyncTask();
        listarEventosAsyncTask.execute();

    }
    public class ListarEventosAsyncTask extends AsyncTask<String, String, String> {
        String api_token, query;
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;
        final String URL_WEB_SERVICES = con.getBuscaEvento();
        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;
        int response_code;
        public ListarEventosAsyncTask( ){
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_categoria", categoria);
            builder.appendQueryParameter("api_endereco", endereco);
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
            Log.i("APIListar", "onPostExecute()--> Result: " + result);
            try {
                Evento evento;
                JSONArray jsonArray = new JSONArray(result);
                eventoList = new ArrayList<>();
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        evento = new Evento(jsonObject.getInt("idCancha"),
                                jsonObject.getString("nomeEvento"),
                                jsonObject.getString("dataHora"),
                                jsonObject.getString("endereco"),
                                jsonObject.getString("categoria"),
                                jsonObject.getInt("idEvento"));
                        eventoList.add(evento);
                        Log.i("APIListar", "Evento: -> " + evento.getIdCancha() + " - " +evento.getNomeEvento());
                    }
                    initial();
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }

        public void initial(){
            adapterEventosPersonalizado = new AdapterEventosPersonalizado(eventoList,getApplicationContext(),idpessoa);
            listView.setAdapter(adapterEventosPersonalizado);
            listView.setLayoutManager(new LinearLayoutManager(tela_lista_todos_eventos.this));
        }
    }

}

