package com.example.vamosjogarv1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjogarv1.R;
//import com.example.vamosjogarv1.model.AdapterEventosAnterioresPersonalizado;
//import com.example.vamosjogarv1.model.AdapterEventosPersonalizado;
import com.example.vamosjogarv1.model.Categoria;
import com.example.vamosjogarv1.model.Evento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tela_lista_eventos_anteriores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_eventos_anteriores);
        ListView lista = (ListView) findViewById(R.id.lista3);
//        AdapterEventosAnterioresPersonalizado adapter = new AdapterEventosAnterioresPersonalizado(eventos, eventos, this);
//        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(tela_lista_eventos_anteriores.this, tela_detalhe_evento_2.class);
                startActivity(it);
                finish();
            }
        });
    }

}

