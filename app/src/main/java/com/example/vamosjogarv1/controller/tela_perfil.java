package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vamosjogarv1.R;
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

public class tela_perfil extends AppCompatActivity {
Button btnAvaliacoes;
String pessoaa;
Pessoa pessoa = new Pessoa();
    int idPessoa;
    String  nomePessoa,habilidade,senha,email;
    EditText nome_,senha_,habilidade_;
   EditText editNome;
    EditText editSenha;
    EditText editHabilidade;
    TextView email_,editEmail;
    EditarDadosAsyncTask editarDadosAsyncTask;
    connection con = new connection();
    List<Pessoa> pessoaList;
    boolean control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getInt("IDPESSOA");
        nomePessoa = extras.getString("NOMEPESSOA");
        habilidade = extras.getString("HABILIDADE");
        senha = extras.getString("SENHA");
        email = extras.getString("EMAIL");

        nome_ = (EditText) findViewById(R.id.idNome);
        email_ = (TextView) findViewById(R.id.idEmail);
        senha_ = (EditText) findViewById(R.id.idSenha);
        habilidade_ = (EditText) findViewById(R.id.idHabilidade);
        popularEdit(nomePessoa,email,senha,habilidade);



    }


    public void popularEdit(String nomePessoa, String email,String senha,String habilidade){
        nome_.setText(nomePessoa);
        email_.setText(email);
        senha_.setText(senha);
        habilidade_ .setText(habilidade);
    }

    public void limparEdit(String nomePessoa, String email,String senha,String habilidade){
        nome_.setText("");
        email_.setText("");
        senha_.setText("");
        habilidade_ .setText("");
    }



    public void editar(View view) throws InterruptedException {
        editEmail= (TextView)  findViewById(R.id.idEmail);
        editSenha = (EditText) findViewById(R.id.idSenha);
        editNome = (EditText) findViewById(R.id.idNome);
        editHabilidade = (EditText) findViewById(R.id.idHabilidade);

        senha = editSenha.getText().toString();
        nomePessoa = editNome.getText().toString();
        habilidade = editHabilidade.getText().toString();

        editarDadosAsyncTask = new EditarDadosAsyncTask();
        editarDadosAsyncTask.execute();

        if(control == true){
            Toast.makeText(tela_perfil.this, "Alterações realizadas com sucesso", Toast.LENGTH_LONG).show();
        }

    }

    public class EditarDadosAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query;
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;
        final String URL_WEB_SERVICES = con.getUrlEditarDados();
        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;
        int response_code;

        public EditarDadosAsyncTask( ){
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("senha", senha);
            builder.appendQueryParameter("habilidade", habilidade);
            builder.appendQueryParameter("nome", nomePessoa);
            builder.appendQueryParameter("idPessoa", String.valueOf(idPessoa));
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
                pessoaList = new ArrayList<>();
                if(result.equals("[]")){
                    limparEdit(nomePessoa,email,senha,habilidade);
                    control = true;
                    popularEdit(nomePessoa,email,senha,habilidade);
                }
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        pessoa = new Pessoa(jsonObject.getInt("idPessoa"),
                                jsonObject.getString("nome"),
                                jsonObject.getString("senha"),
                                jsonObject.getString("habilidade"),
                                jsonObject.getString("sexo"));
                        pessoaList.add(pessoa);
                        Log.i("APIListar", "detalhe: -> " + pessoa.getIdPessoa() + " - " +pessoa.getNome());
                    }
                    Toast.makeText(tela_perfil.this, pessoaList.size() + " local Listados no LogCat", Toast.LENGTH_LONG)
                            .show();
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }
    }

}
