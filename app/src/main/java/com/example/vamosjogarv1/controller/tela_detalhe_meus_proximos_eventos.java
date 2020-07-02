package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tela_detalhe_meus_proximos_eventos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_meus_proximos_eventos);
    }

    public void cancelarparticipar(View v){
        Intent it = new Intent(tela_detalhe_meus_proximos_eventos.this, tela_inicial_.class);
        startActivity(it);

    }
}
