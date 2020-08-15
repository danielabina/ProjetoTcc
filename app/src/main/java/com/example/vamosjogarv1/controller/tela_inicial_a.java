package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tela_inicial_a extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_a);
    }

    public void MeusEvento(View view) {
        Intent it = new Intent(tela_inicial_a.this, tela_menu_eventos.class);
        startActivity(it);
    }
    public void BuscarEvento(View view) {
        Intent it = new Intent(tela_inicial_a.this, tela_buscar_evento.class);
        startActivity(it);
    }

    public void CadastrarEvento(View view) {
        Intent it = new Intent(tela_inicial_a.this, tela_cadastrar_evento.class);
        startActivity(it);
    }
}
