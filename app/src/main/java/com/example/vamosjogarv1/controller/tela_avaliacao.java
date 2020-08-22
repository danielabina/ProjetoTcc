package com.example.vamosjogarv1.controller;

import android.content.Intent;
import android.os.Bundle;

//import com.example.vamosjogarv1.model.AdapterEventosPersonalizado;
import com.example.vamosjogarv1.model.Categoria;
import com.example.vamosjogarv1.model.Evento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vamosjogarv1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tela_avaliacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avaliacao);


        ListView lista = (ListView) findViewById(R.id.lista);
        List<Evento> eventos = todosOsEventos();
//        AdapterAvaliacaoPersonalizado adapter = new AdapterAvaliacaoPersonalizado(eventos, eventos, this);
//        lista.setAdapter(adapter);

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
