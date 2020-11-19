package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.CreditCard;
import com.example.vamosjogarv1.model.Evento;
import com.example.vamosjogarv1.model.Local;
import com.example.vamosjogarv1.model.PaymentConnection;
import com.squareup.picasso.Picasso;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import cieloecommerce.sdk.Merchant;
import cieloecommerce.sdk.ecommerce.CieloEcommerce;
import cieloecommerce.sdk.ecommerce.Customer;
import cieloecommerce.sdk.ecommerce.Environment;
import cieloecommerce.sdk.ecommerce.Payment;
import cieloecommerce.sdk.ecommerce.Sale;
import cieloecommerce.sdk.ecommerce.request.CieloError;
import cieloecommerce.sdk.ecommerce.request.CieloRequestException;
import cieloecommerce.sdk.ecommerce.request.UpdateSaleResponse;
import cn.carbs.android.library.MDDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class tela_metodo_pagamento extends AppCompatActivity{
    Button btnProximoListar;
    EditText numeroCartao,nome,mes,ano,cvv;
    int idLocal;
    String idPessoa,nomeEvento,dataHora,valor,foto,dataHoraUm;
    TextView nomePag,pricePag,dataLocalPag;
    ImageView imgPag;
    connection con = new connection();
    List<Evento> eventoList;
    CadastrarEventoAsyncTask cadastrarEventoAsyncTask;
    CreditCard creditCard = new CreditCard();
    Boolean cont;
    Evento evento;
    Double valorPorcemtagem,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_metodo_pagamento);
        Bundle it = getIntent().getExtras();
        idLocal= it.getInt("ID");
        idPessoa= it.getString("IDPESSOA");
        dataHora= it.getString("dataHora");
        dataHoraUm= it.getString("dataHoraUm");
        nomeEvento = it.getString("nomeEvento");
         valor = it.getString("VALOR");
         valorPorcemtagem = Double.parseDouble(valor);
        double percentual = 10 / 100;
        total = percentual * valorPorcemtagem;
        foto = it.getString("FOTO");

        numeroCartao = (EditText) findViewById(R.id.card_number );
        nome=(EditText) findViewById(R.id.name );
        mes=(EditText) findViewById(R.id.month);
        ano=(EditText) findViewById(R.id.year);
        cvv= (EditText) findViewById(R.id.cvv);

        nomePag = (TextView) findViewById(R.id.nameLocalPag);
        pricePag = (TextView) findViewById(R.id.pricePag);
        dataLocalPag = (TextView) findViewById(R.id.dataLocalPag);
        initial();

    }
    public void initial(){
        nomePag.setText(nomeEvento);
        pricePag.setText(String.valueOf(total));
        dataLocalPag.setText(dataHora);
    }
    public void confirmarPagamento(){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Confirmar Pagamento");
        msgBox.setIcon(android.R.drawable.ic_menu_add);
        msgBox.setMessage("Tem Certeza que deseja confirmar o pagamento?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(pagar()== true){
                    Toast.makeText(tela_metodo_pagamento.this,"Pagamento confirmado com sucesso!",Toast.LENGTH_SHORT).show();
                    try {
                        if(gerarEvento() == true){
                            //if(adcListaEvento(evento)) {
                                Toast.makeText(tela_metodo_pagamento.this, "Cadastro de evento confirmado com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(tela_metodo_pagamento.this, tela_pagamento_ok.class);
                                it.putExtra("IDPESSOA", idPessoa);
                                startActivity(it);
                                finish();
                           // }
                        }else{
                            Toast.makeText(tela_metodo_pagamento.this,"OPS! algo deu errado no para cadstrar evento,tente novamente ",Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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


    public void buy( View view ){
        new MDDialog.Builder(this)
                .setTitle("Pagamento")
                .setContentView(R.layout.payment)
                .setNegativeButton("Cancelar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setPositiveButton("Finalizar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmarPagamento();
                    }
                })
                .create()
                .show();
    }

    public void buy( ){
        creditCard.setCardNumber("4024007197692931");
        creditCard.setName("Daniela");
        creditCard.setMonth("12");
        creditCard.setYear("2021");
        creditCard.setCvv("123");
    }
    private boolean gerarEvento() throws InterruptedException {
        cadastrarEventoAsyncTask = new CadastrarEventoAsyncTask();
        cadastrarEventoAsyncTask.execute();
        return true;
    }
    public boolean pagar() {
        buy();
        CieloPagar();
        if(cont){
            return true;
        }
        return false;
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
            builder.appendQueryParameter("dataHoraUm", String.valueOf(dataHoraUm));
            builder.appendQueryParameter("valor", valor);
            builder.appendQueryParameter("nomeEvento", nomeEvento);
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
                eventoList = new ArrayList<>();
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        evento = new Evento(jsonObject.getInt("idEvento"),
                                jsonObject.getInt("idCancha"),
                                jsonObject.getInt("idPessoa"));
                        eventoList.add(evento);
                    }
                }
            } catch (Exception e) {
                Log.i("APIListar", "onPostExecute()--> " + e.getMessage());
            }
        }
    }

    public void CieloPagar( ){
        // Configure seu merchant
        Merchant merchant = new Merchant("ca1eb86b-e1c7-4f7d-af09-187fc334ac9e", "EYRUOQZXXPXRXZYLMSNTIJNKYRDNXPCXQNFSGCWK");

// Crie uma instância de Sale informando o ID do pagamento
        Sale sale = new Sale(idPessoa.concat(String.valueOf(idLocal)));

// Crie uma instância de Customer informando o nome do cliente
        Customer customer = sale.customer(creditCard.getName());

// Crie uma instância de Payment informando o valor do pagamento
        Payment payment = sale.payment(100);

// Crie  uma instância de Credit Card utilizando os dados de teste
// esses dados estão disponíveis no manual de integração
        payment.creditCard(creditCard.getCvv(), "Visa").setExpirationDate(creditCard.getMonth().concat("/").concat(creditCard.getYear()))
                .setCardNumber(creditCard.getCardNumber())
                .setHolder(creditCard.getName());

// Crie o pagamento na Cielo
        try {
            // Configure o SDK com seu merchant e o ambiente apropriado para criar a venda
            sale = new CieloEcommerce(merchant, Environment.SANDBOX).createSale(sale);

            // Com a venda criada na Cielo, já temos o ID do pagamento, TID e demais
            // dados retornados pela Cielo
            String paymentId = sale.getPayment().getPaymentId();

            // Com o ID do pagamento, podemos fazer sua captura, se ela não tiver sido capturada ainda
            UpdateSaleResponse saleUp = new CieloEcommerce(merchant, Environment.SANDBOX).captureSale(paymentId, 15700, 0);

            // E também podemos fazer seu cancelamento, se for o caso
            saleUp = new CieloEcommerce(merchant, Environment.SANDBOX).cancelSale(paymentId, 15700);
            if(saleUp.getReasonMessage().equals("Successful")){
                cont = true;
            }else{
                cont = false;
            }
        } catch (ExecutionException | InterruptedException e) {
            // Como se trata de uma AsyncTask, será preciso tratar ExecutionException e InterruptedException
            e.printStackTrace();
        } catch (CieloRequestException e) {
            // Em caso de erros de integração, podemos tratar o erro aqui.
            // os códigos de erro estão todos disponíveis no manual de integração.
            CieloError error = e.getError();

            Log.v("Cielo", error.getCode().toString());
            Log.v("Cielo", error.getMessage());

            if (error.getCode() != 404) {
                Log.e("Cielo", null, e);
            }
        }
    }
}
