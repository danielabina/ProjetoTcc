package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tela_pagamento_ok extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ok_pagamento);
    }

    public void voltarTelaInicial(View view){
        Intent it = new Intent(tela_pagamento_ok.this, tela_inicial_a.class);
        startActivity(it);
        finish();
    }
}
