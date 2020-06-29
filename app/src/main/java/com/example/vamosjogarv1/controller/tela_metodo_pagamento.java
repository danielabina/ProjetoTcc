package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class tela_metodo_pagamento extends AppCompatActivity {
Button btnProximoListar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_metodo_pagamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       btnProximoListar = (Button) findViewById(R.id.btnProximoLista);

       btnProximoListar.setOnClickListener(new View.OnClickListener() {         @Override
           public void onClick(View v) {
               Intent it = new Intent(tela_metodo_pagamento.this, tela_inicial_.class);
               startActivity(it);
               finish();
           }
       });
    }


}
