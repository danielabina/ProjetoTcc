package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.CreditCard;
import com.example.vamosjogarv1.model.Evento;
import com.example.vamosjogarv1.model.PaymentConnection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class tela_metodo_pagamento extends AppCompatActivity implements Observer {
Button btnProximoListar;
EditText numeroCartao,nome,mes,ano,cvv,parcelas;
int idLocal;
String idPessoa,nomeEvento,dataHora;
    connection con = new connection();
    List<Evento> eventoList;
    CadastrarEventoAsyncTask cadastrarEventoAsyncTask;
    InsertParticipanteEvento insertParticipanteEvento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_metodo_pagamento);
        Bundle it = getIntent().getExtras();
        idLocal= it.getInt("ID");
        idPessoa= it.getString("IDPESSOA");
        dataHora= it.getString("dataHora");
        nomeEvento = it.getString("nomeEvento");

        numeroCartao = (EditText) findViewById(R.id.card_number );
        nome=(EditText) findViewById(R.id.name );
        mes=(EditText) findViewById(R.id.month);
        ano=(EditText) findViewById(R.id.year);
        cvv= (EditText) findViewById(R.id.cvv);
        parcelas= (EditText) findViewById(R.id.parcels);





    }
    public void confirmarPagamento(){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Confirmar Pagamento");
        msgBox.setIcon(android.R.drawable.ic_menu_add);
        msgBox.setMessage("Tem Certeza que deseja confirmar o pagamento?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //buy();
                if(pagar()== true){
                    Toast.makeText(tela_metodo_pagamento.this,"Pagamento confirmado com sucesso!",Toast.LENGTH_SHORT).show();
                    if(gerarEvento() == true){
                        Toast.makeText(tela_metodo_pagamento.this,"Cadastro de evento confirmado com sucesso!",Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(tela_metodo_pagamento.this, tela_pagamento_ok.class);
                        startActivity(it);
                        finish();
                      }else{
                           Toast.makeText(tela_metodo_pagamento.this,"OPS! algo deu errado no para cadstrar evento,tente novamente ",Toast.LENGTH_SHORT).show();
                     }
                        }else{
                     Toast.makeText(tela_metodo_pagamento.this,"OPS! algo deu errado no pagamento,tente novamente ",Toast.LENGTH_SHORT).show();
                }

            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_metodo_pagamento.this,"Voce cancelou o pagemento ",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_metodo_pagamento.this, tela_metodo_pagamento.class);
                startActivity(it);
            }
        });
        msgBox.show();


    }


    public void pagamento(View view){
        confirmarPagamento();
    }
    public void buy( ){
                        buttonBuying( true );
                        CreditCard creditCard = new CreditCard( tela_metodo_pagamento.this );
                        creditCard.setCardNumber("5428208631898204");
                        creditCard.setName("daniela souza");
                        creditCard.setMonth("02");
                        creditCard.setYear("2028");
                        creditCard.setCvv("841");
                        creditCard.setParcels(1);

                        getPaymentToken( creditCard );
                    }


    private void getPaymentToken( CreditCard creditCard ){
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled( true );
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface( creditCard, "Android" );
        webView.loadUrl("file:///android_asset/index.html");
    }

    private void showMessage( final String message ){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText( tela_metodo_pagamento.this, message, Toast.LENGTH_LONG ).show();
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        CreditCard creditCard = (CreditCard) o;

        if( creditCard.getToken() == null ){
            buttonBuying( false );
            showMessage( creditCard.getError() );
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(con.getUrlPagamento())
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        PaymentConnection paymentConnection = retrofit.create(PaymentConnection.class);
        Call<String> requester = paymentConnection.sendPayment(
                Integer.toString(idLocal),
                1,
                creditCard.getToken(),
                creditCard.getParcels()
        );

        requester.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                buttonBuying( false );
                showMessage( response.body() );
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                buttonBuying( false );
                Log.e("log", "Error: "+t.getMessage());
            }
        });
    }

    private void buttonBuying( final boolean status ){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String label;

                //label = getResources().getString(R.string.);
                if( status ){
                    //label = getResources().getString(R.string.button_buying);
                }

              //  ((Button) findViewById(R.id.button_buy)).setText(label);
            }
        });
    }

    private boolean gerarEvento()
    {
        cadastrarEventoAsyncTask = new CadastrarEventoAsyncTask();
        cadastrarEventoAsyncTask.execute();
        return true;
    }

    private boolean pagar() {
        return true;
    }

    public class CadastrarEventoAsyncTask extends AsyncTask<String, String, String> {

        String api_token, query;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = con.getUrlCadastrarEvento();

        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;

        int response_code;


        public CadastrarEventoAsyncTask( ){

            this.builder = new Uri.Builder();

            builder.appendQueryParameter("idPessoa", idPessoa);
            builder.appendQueryParameter("idCancha", String.valueOf(idLocal));
            builder.appendQueryParameter("dataHora", String.valueOf(dataHora));
            builder.appendQueryParameter("nomeEvento", nomeEvento);

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

                Evento evento;

                JSONArray jsonArray = new JSONArray(result);

                eventoList = new ArrayList<>();

                if (jsonArray.length() != 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        evento = new Evento(jsonObject.getInt("idEvento"),
                                jsonObject.getInt("idCancha"),
                                jsonObject.getInt("idPessoa"));

                        eventoList.add(evento);
                        adcListaEvento(evento);
                    }
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }
    }

    public void adcListaEvento(Evento evento){
        insertParticipanteEvento = new InsertParticipanteEvento(evento);
        insertParticipanteEvento.execute();
    }

    public class InsertParticipanteEvento extends AsyncTask<String, String, String> {
        String api_token, query;
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        final String URL_WEB_SERVICES = con.getInserirParticipanteEvento();
        final int READ_TIMEOUT = 10000; // MILISSEGUNDOS
        final int CONNECTION_TIMEOUT = 30000;
        int response_code;
        public InsertParticipanteEvento(Evento evento){
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("api_idEvento", String.valueOf(evento.getIdEvento()));
            builder.appendQueryParameter("api_idPessoa", String.valueOf(evento.getIdPessoa()));
            builder.appendQueryParameter("api_idCancha", String.valueOf(evento.getIdCancha()));
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
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }
    }
}
