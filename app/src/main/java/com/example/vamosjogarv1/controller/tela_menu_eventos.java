package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.vamosjogarv1.R;
import android.view.View;
import android.widget.Button;

public class tela_menu_eventos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu_eventos);


        Button btnanterior;
        btnanterior = findViewById(R.id.btnanterior);
        btnanterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_menu_eventos.this, tela_lista_eventos_anteriores.class);
                startActivity(it);
            }
        });
    }

//    public void EventosAnteriores(View view) {
//        Intent it = new Intent(tela_menu_eventos.this, tela_lista_eventos_anteriores.class);
//        startActivity(it);
//    }
    public void ProximosEventos(View view) {
        Intent it = new Intent(tela_menu_eventos.this, tela_meus_proximos_eventos.class);
        startActivity(it);
    }
}
