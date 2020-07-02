package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tela_detalhe_evento_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_evento_2);
    }

    public void Avaliar(View view) {
        Intent it = new Intent(tela_detalhe_evento_2.this, tela_avaliar.class);
        startActivity(it);
    }
}
