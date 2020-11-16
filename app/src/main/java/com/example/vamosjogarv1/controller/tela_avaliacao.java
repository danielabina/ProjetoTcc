package com.example.vamosjogarv1.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

//import com.example.vamosjogarv1.model.AdapterEventosPersonalizado;
import com.example.vamosjogarv1.model.AdapterAvaliacaoPersonalizado;
import com.example.vamosjogarv1.model.AdapterEventosPersonalizado;
import com.example.vamosjogarv1.model.Avaliacao;
import com.example.vamosjogarv1.model.Categoria;
import com.example.vamosjogarv1.model.Evento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vamosjogarv1.R;

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

public class tela_avaliacao extends AppCompatActivity {
    AdapterAvaliacaoPersonalizado adapterAvaliacaoPersonalizado;
    List<Avaliacao> avaliacaoList;
    Avaliacao avaliacao;
    int idPessoa;
    connection con = new connection();
    ListarAvaliacaoAsyncTask listarAvaliacaoAsyncTask;
    RecyclerView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avaliacao);
        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getInt("IDPESSOA");
        listView = findViewById(R.id.recyclerViewAvaliacao);
        listarAvaliacaoAsyncTask = new ListarAvaliacaoAsyncTask();
        listarAvaliacaoAsyncTask.execute();

    }

    public class ListarAvaliacaoAsyncTask extends AsyncTask<String, String, String> {
        String api_token, query;
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;
        final String URL_WEB_SERVICES = con.getUrlBuscarAvaliacao();
        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;
        int response_code;
        public ListarAvaliacaoAsyncTask( ){
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
            Log.i("APIListar", "onPostExecute()--> Result: " + result);
            try {
                if(result.contains("nenhum")){
                    Toast.makeText(tela_avaliacao.this, "Voce não possui nenhuma avaliação ainda", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(tela_avaliacao.this, tela_inicial_a.class);
                    it.putExtra("IDPESSOA", idPessoa);
                    it.putExtra("telaAva", 1);
                    startActivity(it);
                    finish();

                }else {
                    Avaliacao avaliacao;
                    JSONArray jsonArray = new JSONArray(result);
                    avaliacaoList = new ArrayList<>();
                    if (jsonArray.length() != 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            avaliacao = new Avaliacao(jsonObject.getInt("idAvaliacao"),
                                    jsonObject.getString("comentario"),
                                    jsonObject.getInt("valorEstrela"),
                                    jsonObject.getString("nomeEvento"));
                            avaliacaoList.add(avaliacao);
                        }
                    }
                    initial();
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }

        public void initial(){
            adapterAvaliacaoPersonalizado = new AdapterAvaliacaoPersonalizado(avaliacaoList,getApplicationContext());
            listView.setAdapter(adapterAvaliacaoPersonalizado);
            listView.setLayoutManager(new LinearLayoutManager(tela_avaliacao.this));
        }
    }

}
