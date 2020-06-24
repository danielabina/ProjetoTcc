package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.AdapterEventosPersonalizado;
import com.example.vamosjogarv1.model.AdapterLocalPersonalizado;
import com.example.vamosjogarv1.model.Categoria;
import com.example.vamosjogarv1.model.Evento;
import com.example.vamosjogarv1.model.Local;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tela_lista_todos_eventos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_todos_eventos);
        ListView lista = (ListView) findViewById(R.id.lista);
        List<Evento> eventos = todosOsEventos();
        AdapterEventosPersonalizado adapter = new AdapterEventosPersonalizado(eventos, eventos, this);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(tela_lista_todos_eventos.this, tela_detalhe_local.class);
                startActivity(it);
            }
        });
    }

    /**
     * Exemplo qualquer de devolução de uma lista de cursos.
     * Para esse exemplo será considerado um hard coded.
     *
     * @return lista com todos os cursos
     */
    private List<Evento> todosOsEventos() {
        return new ArrayList<>(Arrays.asList(
                new Evento("Java", "básico de Java", Categoria.Voleibol),
                new Evento("HTML e CSS", "HTML 5 e suas novidades", Categoria.Futebol),
                new Evento("Android", "boas de práticas",Categoria.Handebol)));
    }
}

