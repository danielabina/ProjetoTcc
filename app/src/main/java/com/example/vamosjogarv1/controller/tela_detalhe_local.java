package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tela_detalhe_local extends AppCompatActivity {
    Button btnproximook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_local);
        btnproximook = (Button) findViewById(R.id.btnproximook);

        btnproximook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_detalhe_local.this, tela_lista_todos_eventos.class);
                startActivity(it);
            }
        });
    }
}
