package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Evento;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.health.TimerStat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class tela_detalhe_evento extends AppCompatActivity {
String idPessoa,dataHoraEv,modalidade,endereco,nome;
    TextView dataHoraEvento,modalidadee,enderecoEvento,nomeEventoo;
    int idEvento,idCancha;
    boolean controlador = false;
    connection con = new connection();
    InsertPartiparEvento insertPartiparEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_evento);

        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getString("IDPESSOA");
        idCancha = extras.getInt("IDCANCHA");
        dataHoraEv = extras.getString("dataHoraEv");
        modalidade = extras.getString("categoria");
        nome = extras.getString("nomeEvento");
        endereco = extras.getString("endereco");
        idEvento = extras.getInt("IDEVENTO");


        nomeEventoo = (TextView) findViewById(R.id.nomeEventoo);
        enderecoEvento = (TextView)  findViewById(R.id.enderecoEvento);
        modalidadee = (TextView) findViewById(R.id.modalidade);
        dataHoraEvento = (TextView) findViewById(R.id.dataHoraEvento);

        initial();
        Button btnParticipar;
        btnParticipar = findViewById(R.id.btnParticipar);
        btnParticipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participar();
            }
        });
    }

    public void participar(){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Confirmar Participação");
        msgBox.setIcon(android.R.drawable.ic_menu_add);
        msgBox.setMessage("Tem Certeza que deseja participar desse evento?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //insert de participação
                insertPartiparEvento = new InsertPartiparEvento();
                insertPartiparEvento.execute();
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_detalhe_evento.this,"Voce cancelou a participação desse evento ",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_detalhe_evento.this, tela_detalhe_evento.class);
                startActivity(it);
            }
        });
        msgBox.show();


    }

    public void initial(){
        nomeEventoo.setText(nome);
        modalidadee.setText(modalidade);
        enderecoEvento.setText(endereco);
        dataHoraEvento.setText(dataHoraEv);
    }

    public void participantes(View view){
        Intent it = new Intent(tela_detalhe_evento.this, tela_listar_todos_participantes_evento.class);
        it.putExtra("IDEVENTO", idEvento);
        startActivity(it);
    }

    public class InsertPartiparEvento extends AsyncTask<String, String, String> {
        String api_token, query;
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = con.getInserirParticipanteEvento();
        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;
        int response_code;
        public InsertPartiparEvento( ){
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_idEvento", Integer.toString(idEvento));
            builder.appendQueryParameter("api_idPessoa", idPessoa);
            builder.appendQueryParameter("api_idCancha", Integer.toString(idCancha));
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
                if(!result.contains("<br />")){
                    controlador = true;
                }

                if(controlador == true) {
                    Toast.makeText(tela_detalhe_evento.this,"Muito bem! estamos ansiosos pelo dia!",Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(tela_detalhe_evento.this, tela_participacao_ok.class);
                    startActivity(it);
                    finish();
                }else{
                    Toast.makeText(tela_detalhe_evento.this,"NÃO FOI POSSIVEL PARTICIPAR DE EVENTO,TENTE NOVAMENTE!",Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }
    }

}
