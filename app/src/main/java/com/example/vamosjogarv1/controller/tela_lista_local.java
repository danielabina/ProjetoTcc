package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.AdapterLocalPersonalizado;
import com.example.vamosjogarv1.model.Cancha;
import com.example.vamosjogarv1.model.Local;
import com.google.gson.JsonArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class tela_lista_local extends AppCompatActivity {


    AdapterLocalPersonalizado adapterLocalPersonalizado;
    List<Local> localList;
    Local local;
    String endereco,categoria,token,dataHora,idpessoa,nomeEvento;



    connection con = new connection();
    ListarLocalAsyncTask listarLocaisAsyncTask;
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_local);
        token = "tcc";
        Intent it = getIntent();
         categoria = it.getStringExtra("categoria");
         endereco = it.getStringExtra("endereco");
        dataHora = it.getStringExtra("dataHora");
        idpessoa = it.getStringExtra("IDPESSOA");
        nomeEvento = it.getStringExtra("nomeEvento");
        listView = findViewById(R.id.recyclerViewLocal);

        listarLocaisAsyncTask = new ListarLocalAsyncTask();
        listarLocaisAsyncTask.execute();

    }



    public class ListarLocalAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = con.getBuscaLocal();

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public ListarLocalAsyncTask( ){

            this.builder = new Uri.Builder();

            builder.appendQueryParameter("api_categoria", categoria);
                builder.appendQueryParameter("api_endereco", endereco);
            builder.appendQueryParameter("api_dataHora", dataHora);

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

                Local local;

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

                        Log.i("APIListar", "Estado: -> " + local.getId() + " - " +local.getNome());


                    }

                    Toast.makeText(tela_lista_local.this, localList.size() + " local Listados no LogCat", Toast.LENGTH_LONG)
                            .show();

                    initial();

                }

            } catch (Exception e) {


                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());


            }


        }

        public void initial(){
            adapterLocalPersonalizado = new AdapterLocalPersonalizado(localList,getApplicationContext(),idpessoa,dataHora,nomeEvento);
            listView.setAdapter(adapterLocalPersonalizado);
            listView.setLayoutManager(new LinearLayoutManager(tela_lista_local.this));
        }
    }
}
