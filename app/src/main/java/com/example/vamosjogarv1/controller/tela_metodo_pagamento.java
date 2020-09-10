package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.CreditCard;
import com.example.vamosjogarv1.model.PaymentConnection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.util.Observable;
import java.util.Observer;

import cn.carbs.android.library.MDDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class tela_metodo_pagamento extends AppCompatActivity implements Observer {
Button btnProximoListar;
String idPessoa;
EditText numeroCartao,nome,mes,ano,cvv,parcelas;
int idLocal,valor;
connection con ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_metodo_pagamento);
        Bundle it = getIntent().getExtras();
        idLocal= it.getInt("ID");
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
                buy();
                Toast.makeText(tela_metodo_pagamento.this,"Pagamento confirmado com sucesso!",Toast.LENGTH_SHORT).show();
//                if(pagar()== true){
//                    if(gerarEvento() == true){
//                        Intent it = new Intent(tela_metodo_pagamento.this, tela_ok_pagamento.class);
//                        startActivity(it);
//                        finish();
//                    }else{
//                        Toast.makeText(tela_metodo_pagamento.this,"OPS! algo deu errado no para cadstrar evento,tente novamente ",Toast.LENGTH_SHORT).show();
//
//                    }
//                }else{
//                    Toast.makeText(tela_metodo_pagamento.this,"OPS! algo deu errado no pagamento,tente novamente ",Toast.LENGTH_SHORT).show();
//
//                }

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

    private boolean gerarEvento() {
        return true;
    }

    private boolean pagar() {
        return true;
    }


}
