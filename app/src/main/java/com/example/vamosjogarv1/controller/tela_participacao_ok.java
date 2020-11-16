package com.example.vamosjogarv1.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vamosjogarv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tela_participacao_ok extends AppCompatActivity {
    String idPessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_participacao_ok);
        Bundle extras = getIntent().getExtras();
        idPessoa = extras.getString("IDPESSOA");
    }

    public void voltarTelaInicial2(View view){
        Intent it = new Intent(tela_participacao_ok.this, tela_inicial_a.class);
        it.putExtra("IDPESSOA", idPessoa);
        it.putExtra("telaEvento", 1);
        startActivity(it);
        finish();
    }
}
