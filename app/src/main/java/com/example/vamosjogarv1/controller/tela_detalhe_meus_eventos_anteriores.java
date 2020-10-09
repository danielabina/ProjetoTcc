package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vamosjogarv1.R;


public class tela_detalhe_meus_eventos_anteriores extends AppCompatActivity {

    String idPessoa, dataHoraEv,idEvento,  modalidade, endereco, nome;
    TextView dataHoraEvento, modalidadee, enderecoEvento, nomeEventoo;
    int idCancha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_meus_eventos_anteriores);

        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getString("IDPESSOA");
        idCancha = extras.getInt("IDCANCHA");
        dataHoraEv = extras.getString("dataHoraEv");
        modalidade = extras.getString("categoria");
        nome = extras.getString("nomeEvento");
        endereco = extras.getString("endereco");
        idEvento = extras.getString("IDEVENTO");


        nomeEventoo = (TextView) findViewById(R.id.nomeEventooAnterior);
        enderecoEvento = (TextView) findViewById(R.id.enderecoEventoAnterior);
        modalidadee = (TextView) findViewById(R.id.modalidadeAnterior);
        dataHoraEvento = (TextView) findViewById(R.id.dataHoraEventoAnterior);
        initial();
        Button btnParticipantesAnterior;
        btnParticipantesAnterior = findViewById(R.id.btnParticipantesAnterior);
        btnParticipantesAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_detalhe_meus_eventos_anteriores.this, tela_lista_avaliacao.class);
                it.putExtra("IDPESSOA", idPessoa);
                it.putExtra("IDEVENTO",idEvento);
                startActivity(it);
            }
        });
    }

    public void initial() {
        nomeEventoo.setText(nome);
        modalidadee.setText(modalidade);
        enderecoEvento.setText(endereco);
        dataHoraEvento.setText(dataHoraEv);
    }
}

