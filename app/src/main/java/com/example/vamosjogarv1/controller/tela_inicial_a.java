package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Local;
import com.example.vamosjogarv1.model.Pessoa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
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
import java.util.List;

public class tela_inicial_a extends AppCompatActivity {
String email,idPessoa;
    BuscaIdPessoaAsyncTask buscaIdPessoaAsyncTask;
    connection con = new connection();
    List<Pessoa> pessoaList;
    Pessoa pessoa = new Pessoa();
    int telapag,idPessoaint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_a);



        Intent it = getIntent();
        email = it.getStringExtra("EMAIL");
        telapag = it.getIntExtra("telapag",0);
        if(telapag == 1){
            idPessoa = it.getStringExtra("IDPESSOA");
            idPessoaint = Integer.valueOf(idPessoa);
            pessoa.setIdPessoa(idPessoaint);
        }        buscaIdPessoaAsyncTask = new BuscaIdPessoaAsyncTask();
        buscaIdPessoaAsyncTask.execute();
    }

    public void MeusEvento(View view) {
        Intent it = new Intent(tela_inicial_a.this, tela_menu_eventos.class);
        it.putExtra("IDPESSOA", pessoa.getIdPessoa());
        startActivity(it);
    }
    public void BuscarEvento(View view) {
        Intent it = new Intent(tela_inicial_a.this, tela_buscar_evento.class);
        it.putExtra("IDPESSOA", pessoa.getIdPessoa());
        startActivity(it);
    }

    public void CadastrarEvento(View view) {
        Intent it = new Intent(tela_inicial_a.this, tela_cadastrar_evento.class);
        it.putExtra("IDPESSOA", pessoa.getIdPessoa());
        startActivity(it);
    }

    public void MeusAjustes(View view) {
        buscaIdPessoaAsyncTask = new BuscaIdPessoaAsyncTask();
        buscaIdPessoaAsyncTask.execute();
        Intent it = new Intent(tela_inicial_a.this, tela_menu_ajustes.class);
        it.putExtra("IDPESSOA", pessoa.getIdPessoa());
        it.putExtra("NOMEPESSOA", pessoa.getNome());
        it.putExtra("SENHA", pessoa.getSenha());
        it.putExtra("HABILIDADE", pessoa.getHabilidade());
        it.putExtra("EMAIL", pessoa.getEmail());
        it.putExtra("FOTO", pessoa.getPhoto());
        startActivity(it);

    }

    public void Avaliacoes(View view) {
        Intent it = new Intent(tela_inicial_a.this, tela_avaliacao.class);
        it.putExtra("IDPESSOA", pessoa.getIdPessoa());
        startActivity(it);
    }

    public class BuscaIdPessoaAsyncTask extends AsyncTask<String, String, String> {

        String  query;
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;
        final String URL_WEB_SERVICES = con.getBuscaIdPessoa();
        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;
        int response_code;

        public BuscaIdPessoaAsyncTask( ){
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_email", email);
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
                JSONArray jsonArray = new JSONArray(result);
                pessoaList = new ArrayList<>();
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        pessoa = new Pessoa(jsonObject.getInt("idPessoa"),
                                jsonObject.getString("nome"),
                                jsonObject.getString("email"),
                                jsonObject.getString("senha"),
                                jsonObject.getString("habilidade"),
                                jsonObject.getString("sexo"),
                                jsonObject.getString("photo"));
                        pessoaList.add(pessoa);
                        Log.i("APIListar", "detalhe: -> " + pessoa.getIdPessoa() + " - " +pessoa.getNome());
                    }
                    Toast.makeText(tela_inicial_a.this, " Seja Bem vindo(a)!!"+ pessoa.getNome(), Toast.LENGTH_LONG)
                            .show();
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }
    }

    public void Sair(View view){
        finish();
        SharedPreferences.Editor pref = getSharedPreferences("info", MODE_PRIVATE).edit();

        pref.putString(encrypt("email"), "");

        pref.commit();

        SharedPreferences pref1 = getSharedPreferences("info", MODE_PRIVATE);
        String email = pref1.getString(encrypt("email"), null);

        if(email.isEmpty()) {
            finish();
            Intent abrePrincipal = new Intent(tela_inicial_a.this, tela_login.class);
            startActivity(abrePrincipal);
            finish();
        }
    }

    public String encrypt(String palavra) {

        return Base64.encodeToString(palavra.getBytes(), Base64.DEFAULT);
    }

    public String decrypt(String palavra) {

        return new String(Base64.decode(palavra, Base64.DEFAULT));
    }
}
