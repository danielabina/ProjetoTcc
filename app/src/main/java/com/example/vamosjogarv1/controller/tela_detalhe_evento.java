package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;
import com.example.vamosjogarv1.model.Local;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tela_detalhe_evento extends AppCompatActivity {
String idPessoa,dataHoraEv,modalidade,endereco,nome;
    TextView dataHoraEvento,modalidadee,enderecoEvento,nomeEventoo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_evento);

        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getString("IDPESSOA");
        dataHoraEv = extras.getString("dataHoraEv");
        modalidade = extras.getString("categoria");
        nome = extras.getString("nomeEvento");
        endereco = extras.getString("endereco");


        nomeEventoo = (TextView) findViewById(R.id.nomeEventoo);
        enderecoEvento = (TextView)  findViewById(R.id.enderecoEvento);
        modalidadee = (TextView) findViewById(R.id.modalidade);
        dataHoraEvento = (TextView) findViewById(R.id.dataHoraEvento);

        initial();
        Button btnParticipar;
        btnParticipar = findViewById(R.id.btnParticipar);
        btnParticipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participar();
            }
        });
    }

    public void participar(){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Confirmar Participação");
        msgBox.setIcon(android.R.drawable.ic_menu_add);
        msgBox.setMessage("Tem Certeza que deseja participar desse evento?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_detalhe_evento.this,"Muito bem! estamos ansiosos pelo dia!",Toast.LENGTH_SHORT).show();
                //enviar id pessoa
                //id local
                Intent it = new Intent(tela_detalhe_evento.this, tela_inicial_a.class);
                startActivity(it);
                finish();
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(tela_detalhe_evento.this,"Voce cancelou a participação desse evento ",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(tela_detalhe_evento.this, tela_detalhe_evento.class);
                startActivity(it);
            }
        });
        msgBox.show();


    }

    public void initial(){
        nomeEventoo.setText(nome);
        modalidadee.setText(modalidade);
        enderecoEvento.setText(endereco);
        dataHoraEvento.setText(dataHoraEv);
    }

    public void participantes(){

    }
}
