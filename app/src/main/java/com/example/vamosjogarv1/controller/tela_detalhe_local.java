package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                escolherEvento();
            }
        });
    }

    public void escolherEvento(){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Confirmar Local");
        msgBox.setIcon(android.R.drawable.ic_menu_add);
        msgBox.setMessage("Tem Certeza que deseja escolher esse local para o evento?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_detalhe_local.this,"Muito bem! estamos ansiosos pelo dia!",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_detalhe_local.this, tela_metodo_pagamento.class);
                startActivity(it);
                finish();
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_detalhe_local.this,"Tudo bem,temos varias opções!",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_detalhe_local.this, tela_detalhe_local.class);
                startActivity(it);
            }
        });
        msgBox.show();


    }
}
