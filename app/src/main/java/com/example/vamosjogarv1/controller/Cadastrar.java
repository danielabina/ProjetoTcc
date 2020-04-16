package com.example.vamosjogarv1.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Pessoa;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

@SuppressLint("Registered")
public class Cadastrar extends AppCompatActivity {
    Pessoa pessoa = new Pessoa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_main);

        Button btnCadastrar;
        EditText nome,senha,email;
        btnCadastrar = (Button) findViewById(R.id.btnEntrar);
        email= (EditText)  findViewById(R.id.idEmail);
        senha = (EditText) findViewById(R.id.idSenha);
        nome = (EditText) findViewById(R.id.idNome);


        if(true){
            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent it = new Intent(Cadastrar.this, Login.class);
                    //startActivity(it);

                    String url = "http://192.168.0.114/Login/teste.json";
                    Ion.with(Cadastrar.this)
                            .load(url)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    try {
                                        Toast.makeText(Cadastrar.this , "Retorno" + result.toString(), Toast.LENGTH_LONG).show();

                                    }catch (Exception erro){

                                        Toast.makeText(Cadastrar.this, "OPS OCORREU UM ERRO" + erro, Toast.LENGTH_LONG).show();

                                    }

                                }
                            });
                }
            });
        }else{

        }

    }
}

