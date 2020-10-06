package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.vamosjogarv1.R;
import android.view.View;

public class tela_menu_eventos extends AppCompatActivity {
    int idPessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu_eventos);

        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getInt("IDPESSOA");

    }

    public void EventosAnteriores(View view) {
        Intent it = new Intent(tela_menu_eventos.this, tela_lista_meus_eventos_anteriores.class);
        it.putExtra("IDPESSOA", idPessoa);
       startActivity(it);
   }
    public void ProximosEventos(View view) {
        Intent it = new Intent(tela_menu_eventos.this, tela_lista_meus_proximos_eventos.class);
        it.putExtra("IDPESSOA", idPessoa);
        startActivity(it);
    }
}
