package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tela_pagamento_ok extends AppCompatActivity {
String idPessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ok_pagamento);
        Bundle it = getIntent().getExtras();
        idPessoa= it.getString("IDPESSOA");

    }

    public void voltarTelaInicial(View view){
        Intent it = new Intent(tela_pagamento_ok.this, tela_inicial_a.class);
        it.putExtra("IDPESSOA", idPessoa);
        it.putExtra("telapag",1);
        startActivity(it);
        finish();
    }
}
