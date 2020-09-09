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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
int idLocal,valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_metodo_pagamento);
        Bundle it = getIntent().getExtras();
        idLocal= it.getInt("ID");
        idPessoa = it.getString("IDPESSOA");

       btnProximoListar = (Button) findViewById(R.id.btnProximoLista);

       btnProximoListar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           confirmarPagamento();
           }
       });
    }
    public void confirmarPagamento(){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Confirmar Pagamento");
        msgBox.setIcon(android.R.drawable.ic_menu_add);
        msgBox.setMessage("Tem Certeza que deseja confirmar o pagamento?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_metodo_pagamento.this,"Pagamento confirmado com sucesso!",Toast.LENGTH_SHORT).show();
                if(pagar()== true){
                    if(gerarEvento() == true){
                        Intent it = new Intent(tela_metodo_pagamento.this, tela_ok_pagamento.class);
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

    public void buyy( View view ){
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
                        View root = v.getRootView();

                        buttonBuying( true );
                        CreditCard creditCard = new CreditCard( tela_metodo_pagamento.this );
                        creditCard.setCardNumber( getViewContent( root, R.id.card_number ) );
                        creditCard.setName( getViewContent( root, R.id.name ) );
                        creditCard.setMonth( getViewContent( root, R.id.month ) );
                        creditCard.setYear( getViewContent( root, R.id.year ) );
                        creditCard.setCvv( getViewContent( root, R.id.cvv ) );
                        creditCard.setParcels( Integer.parseInt( getViewContent( root, R.id.parcels ) ) );

                        getPaymentToken( creditCard );
                    }
                })
                .create()
                .show();
    }

    private String getViewContent( View root, int id ){
        EditText field = (EditText) root.findViewById(id);
        return field.getText().toString();
    }

    private void getPaymentToken( CreditCard creditCard ){
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled( true );
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
                .baseUrl("http://192.168.25.221:8888/android-payment/")
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        PaymentConnection paymentConnection = retrofit.create(PaymentConnection.class);
        Call<String> requester = paymentConnection.sendPayment(
                Integer.toString(idLocal),
                valor,
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
