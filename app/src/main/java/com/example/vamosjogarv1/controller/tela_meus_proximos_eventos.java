package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;
//import com.example.vamosjogarv1.model.AdapterEventosAnterioresPersonalizado;
//import com.example.vamosjogarv1.model.AdapterEventosProximosPersonalizado;
import com.example.vamosjogarv1.model.Categoria;
import com.example.vamosjogarv1.model.Evento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tela_meus_proximos_eventos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_meus_proximos_eventos);
        ListView lista = (ListView) findViewById(R.id.lista3);
//        AdapterEventosProximosPersonalizado adapter = new AdapterEventosProximosPersonalizado(eventos, eventos, this);
//        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(tela_meus_proximos_eventos.this, tela_detalhe_meus_proximos_eventos.class);
                startActivity(it);
                finish();
            }
        });
    }


}
