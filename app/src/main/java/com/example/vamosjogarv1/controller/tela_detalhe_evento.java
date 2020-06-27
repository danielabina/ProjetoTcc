package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tela_detalhe_evento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_evento);

        Button btnParticipar;
        btnParticipar = findViewById(R.id.btnParticipar);
        btnParticipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_detalhe_evento.this, tela_lista_todos_eventos.class);
                startActivity(it);
                finish();
            }
        });
    }
}
