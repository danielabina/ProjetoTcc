package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Pessoa;

public class tela_perfil extends AppCompatActivity {
Button btnAvaliacoes;
String pessoaa;
Pessoa pessoa = new Pessoa();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);



//        btnAvaliacoes = (Button) findViewById(R.id.btnAvaliacao);
//        btnAvaliacoes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent(tela_perfil.this, tela_avaliacao.class);
//                startActivity(it);            }
//        });
    }

}
