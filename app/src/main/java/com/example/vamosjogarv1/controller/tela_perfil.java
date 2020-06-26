package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vamosjogarv1.R;

public class tela_perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Button btnavaliacao;
        btnavaliacao = findViewById(R.id.btnavaliacao);
        btnavaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_perfil.this, tela_avaliacao.class);
                startActivity(it);
            }
        });
    }

    public void avaliacoes(View v){
        Intent it = new Intent(tela_perfil.this, tela_avaliacao.class);
        startActivity(it);
    }

    public void editar(View v){
        Intent it = new Intent(tela_perfil.this, tela_perfil.class);
        startActivity(it);
    }
}
