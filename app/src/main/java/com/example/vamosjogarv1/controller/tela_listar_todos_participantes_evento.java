package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.controller.connection;
import com.example.vamosjogarv1.controller.tela_lista_todos_eventos;
import com.example.vamosjogarv1.model.AdapterEventosPersonalizado;
import com.example.vamosjogarv1.model.AdapterParticipantesEventoPersonalizado;
import com.example.vamosjogarv1.model.Evento;
import com.example.vamosjogarv1.model.Pessoa;

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

public class tela_listar_todos_participantes_evento extends AppCompatActivity {
    BuscarParticipantesEventos buscarParticipantesEventos;
    AdapterParticipantesEventoPersonalizado adapterParticipantesEventoPersonalizado;
    connection con = new connection();
    int idEvento;
    List<Pessoa> pessoaList;
    RecyclerView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listar_todos_participantes_evento);
        listView = findViewById(R.id.recyclerViewEventoParticipanteAva);
        Bundle extras = getIntent().getExtras();
        idEvento = extras.getInt("IDEVENTO");
        buscarParticipantesEventos = new BuscarParticipantesEventos();
        buscarParticipantesEventos.execute();
    }
    public class BuscarParticipantesEventos extends AsyncTask<String, String, String> {
        String api_token, query;
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;
        final String URL_WEB_SERVICES = con.getUrlBuscarParticipantesEvento();
        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;
        int response_code;
        public BuscarParticipantesEventos( ){
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_idEvento", Integer.toString(idEvento));
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
                Pessoa pessoa;
                JSONArray jsonArray = new JSONArray(result);
                pessoaList = new ArrayList<>();
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        pessoa = new Pessoa(
                                jsonObject.getString("nome"),
                                jsonObject.getString("habilidade"),
                                jsonObject.getString("sexo"));
                        pessoaList.add(pessoa);
                        Log.i("APIListar", "Estado: -> " + pessoa.getIdPessoa());
                    }
                    initial();
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }

        public void initial(){
            adapterParticipantesEventoPersonalizado = new AdapterParticipantesEventoPersonalizado(pessoaList,getApplicationContext());
            listView.setAdapter(adapterParticipantesEventoPersonalizado);
           listView.setLayoutManager(new LinearLayoutManager(tela_listar_todos_participantes_evento.this));
        }
    }
}
