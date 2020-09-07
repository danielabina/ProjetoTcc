package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vamosjogarv1.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class tela_metodo_pagamento extends AppCompatActivity {
Button btnProximoListar;
String idPessoa;
int idLocal,valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_metodo_pagamento);
        Bundle it = getIntent().getExtras();
        idLocal= it.getInt("ID");
        idPessoa = it.getString("IDPESSOA");

       btnProximoListar = (Button) findViewById(R.id.btnProximoLista);

       btnProximoListar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           confirmarPagamento();
           }
       });
    }
    public void confirmarPagamento(){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Confirmar Pagamento");
        msgBox.setIcon(android.R.drawable.ic_menu_add);
        msgBox.setMessage("Tem Certeza que deseja confirmar o pagamento?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_metodo_pagamento.this,"Pagamento confirmado com sucesso!",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_metodo_pagamento.this, tela_ok_pagamento.class);
                startActivity(it);
                finish();
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_metodo_pagamento.this,"Voce cancelou o pagemento ",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_metodo_pagamento.this, tela_metodo_pagamento.class);
                startActivity(it);
            }
        });
        msgBox.show();


    }


}
